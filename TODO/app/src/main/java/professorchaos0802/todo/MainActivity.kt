package professorchaos0802.todo

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import professorchaos0802.todo.composeui.home.homescreen.HomeScreenView
import professorchaos0802.todo.composeui.list.listscreen.ListScreenView
import professorchaos0802.todo.composeui.profile.ProfileScreenView
import professorchaos0802.todo.composeui.profileimage.profileimagescreen.ProfileImage
import professorchaos0802.todo.composeui.splash.splashscreen.SplashScreenView
import professorchaos0802.todo.composeui.usercusotmization.usercustomizationscreen.UserCustomization
import professorchaos0802.todo.composeui.usernamesetup.usernamesetupscreen.UserNameSetupScreenView
import professorchaos0802.todo.models.ItemViewModel
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.navigation.TodoViews
import professorchaos0802.todo.theme.TodoTheme
import professorchaos0802.todo.utilities.FirebaseUtility
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavHostController
    private lateinit var authListener: FirebaseAuth.AuthStateListener
    private lateinit var sharedPreferences: SharedPreferences

    private val listViewModel: ListViewModel by viewModels()
    private val userModel: UserViewModel by viewModels()
    private val itemViewModel: ItemViewModel by viewModels()

    private val signinLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) {/* empty since the auth listener already responds */ }

    override fun onStart() {
        super.onStart()
        Firebase.auth.addAuthStateListener(authListener)
        sharedPreferences = getSharedPreferences(Constants.THEME_KEY, MODE_PRIVATE)
        userModel.themeEvent.value = sharedPreferences.getString(Constants.THEME_KEY, "")
        Constants.windowManager = windowManager
    }

    /**
     * Removes all Firebase listeners when the app is stopped
     */
    override fun onStop() {
        super.onStop()
        Firebase.auth.removeAuthStateListener(authListener)

        // Make sure there are no Firebase listeners remaining when the app stops using the
        // Dispatchers.IO thread
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                FirebaseUtility.subscriptions.forEach { entry ->
                    FirebaseUtility.removeListener(entry.key)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch{
            withContext(Dispatchers.IO){
                initializeAuthListener()
            }
        }

        setupObservers()

        setContent {
            TodoTheme(
                color = userModel.userTheme.value
            ) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = TodoViews.Splash.route
                    ) {

                        composable(route = TodoViews.Splash.route) {
                            SplashScreenView(
                                themeColor = userModel.userTheme.value
                            )
                        }

                        composable(route = TodoViews.UserNameSetup.route) {
                            UserNameSetupScreenView(
                                userModel = userModel,
                                onNext = {
                                    // Update the user's name to Firebase on the Dispatcher.IO thread
                                    lifecycleScope.launch {
                                        withContext(Dispatchers.IO) {
                                            FirebaseUtility.updateName(userModel)
                                        }
                                    }
                                    Log.d(
                                        Constants.SETUP,
                                        "Navigating to CustomizationView: ${TodoViews.Customization.route}"
                                    )
                                    navController.navigate(TodoViews.Customization.route)
                                },
                                onCancel = {
                                    Log.d(
                                        Constants.SETUP,
                                        "Logging out from UserNameSetupScreenView"
                                    )
                                    navController.navigate(TodoViews.Splash.route)
                                    Firebase.auth.signOut()
                                    userModel.user = null
                                },
                            )

                            if (!hasPermissions()) {
                                askForPermissions()
                            }
                        }

                        composable(route = TodoViews.Customization.route) {
                            UserCustomization(
                                userModel = userModel,
                                onNext = {
                                    // Update the user in Firebase on the Dispatchers.IO thread
                                    lifecycleScope.launch {
                                        withContext(Dispatchers.IO) {
                                            FirebaseUtility.updateUser(userModel)
                                            FirebaseUtility.removeListener(Constants.usernamesListenerId)
                                        }
                                    }

                                    Log.d(
                                        Constants.SETUP,
                                        "Navigating to ProfileImageView: ${TodoViews.ProfileImage.route}"
                                    )
                                    navController.navigate(TodoViews.ProfileImage.route)
                                },
                                onCancel = {
                                    Log.d(Constants.SETUP, "Logging out from UserCustomizationView")
                                    navController.navigate(TodoViews.Splash.route)
                                    FirebaseUtility.removeListener(Constants.usernamesListenerId)
                                    Firebase.auth.signOut()
                                    userModel.user = null
                                },
                            )
                        }

                        composable(route = TodoViews.ProfileImage.route) {
                            ProfileImage(
                                userModel = userModel,
                                onNext = {
                                    userModel.user!!.hasCompletedSetup = true

                                    // Update the user and add listeners in Firebase on the Dispatchers.IO thread
                                    lifecycleScope.launch {
                                        withContext(Dispatchers.IO) {
                                            FirebaseUtility.updateUser(userModel)
                                            FirebaseUtility.addListListener(listViewModel.listEvent)
                                            FirebaseUtility.addItemListener(itemViewModel.itemsEvent)
                                        }
                                    }

                                    Log.d(
                                        Constants.SETUP,
                                        "Navigating to HomeView: ${TodoViews.Home.route}"
                                    )

                                    navController.navigate(TodoViews.Home.route)
                                },
                                onCancel = {
                                    Log.d(Constants.SETUP, "Logging out from ProfileImageView")
                                    navController.navigate(TodoViews.Splash.route)
                                    Firebase.auth.signOut()
                                    userModel.user = null
                                },
                                onChooseImage = {
                                    userModel.selectingImage = true
                                    pickMediaActivityResultLauncher.launch("image/*")
                                }
                            )
                        }

                        composable(route = TodoViews.Home.route) {
                            HomeScreenView(
                                userViewModel = userModel,
                                listViewModel = listViewModel,
                                itemViewModel = itemViewModel,
                                onNavigateToSplash = {
                                    lifecycleScope.launch {
                                        withContext(Dispatchers.IO) {
                                            FirebaseUtility.removeListener(Constants.listListenerId)
                                            FirebaseUtility.removeListener(Constants.itemListenerId)
                                        }
                                    }

                                    navController.navigate(TodoViews.Splash.route)
                                },
                                onNavigateToList = {
                                    navController.navigate(TodoViews.List.route)
                                },
                                onNavigateToHome = {}, // Do nothing since we are already on the home screen
                                onNavigateToProfile = {

                                    // Remove the Firebase listeners for all lists on the Dispatchers.IO thread
                                    lifecycleScope.launch {
                                        withContext(Dispatchers.IO) {
                                            FirebaseUtility.removeListener(Constants.listListenerId)
                                            FirebaseUtility.removeListener(Constants.itemListenerId)
                                        }
                                    }

                                    navController.navigate(TodoViews.Profile.route)
                                }
                            )
                        }

                        composable(route = TodoViews.List.route) {
                            ListScreenView(
                                userModel = userModel,
                                listModel = listViewModel,
                                itemModel = itemViewModel,
                                onBackClick = {

                                    // Add Listeners on Dispatchers.IO thread
                                    lifecycleScope.launch {
                                        withContext(Dispatchers.IO) {
                                            FirebaseUtility.addListListener(listViewModel.listEvent)
                                            FirebaseUtility.addItemListener(itemViewModel.itemsEvent)
                                        }
                                    }

                                    navController.navigate(TodoViews.Home.route)
                                }
                            )
                        }

                        composable(route = TodoViews.Profile.route) {
                            ProfileScreenView(
                                userModel = userModel,
                                signout = {
                                    lifecycleScope.launch{
                                        withContext(Dispatchers.IO){
                                            Firebase.auth.signOut()
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }

    }

    /**
     * Sets up observers for all [LiveData] and [MutableLiveData] used in the app
     */
    private fun setupObservers() {
        userModel.theme.observe(this) { newTheme ->
            userModel.userTheme.value = newTheme
        }

        userModel.name.observe(this) { newName ->
            userModel.userName.value = newName
        }

        userModel.image.observe(this) { newImage ->
            userModel.userImage.value = newImage
        }

        userModel.myPublicUsers.observe(this) { pubUsers ->
            userModel.publicUsers.value = pubUsers.filter{
                it.username != userModel.userName.value
            }
        }

        itemViewModel.myItemText.observe(this) { text ->
            itemViewModel.itemText.value = text
        }

        itemViewModel.myItems.observe(this) { allItems ->
            itemViewModel.items.value = allItems
        }

        itemViewModel.currItem.observe(this) { currentItem ->
            itemViewModel.currentItem.value = currentItem
        }

        itemViewModel.currListItems.observe(this) { listItems ->
            itemViewModel.currentListItems.value = listItems
        }

        listViewModel.myLists.observe(this) { allLists ->
            listViewModel.lists.value = allLists
        }

        listViewModel.currList.observe(this) { list ->
            listViewModel.currentList.value = list
            if (list != null) {
                listViewModel.currentListTitleEvent.value = list.title
            }
        }

        listViewModel.currListTitle.observe(this) { title ->
            listViewModel.currentListTitle.value = title
        }
    }

    /**
     * Initializes the AuthListener for a new or current user
     */
    private fun initializeAuthListener() {
        authListener = FirebaseAuth.AuthStateListener { auth: FirebaseAuth ->
            val user = auth.currentUser

            if (user == null) {
                setupAuthUI()
            } else {
                /*
                    Check for the existence of a user, and if there isn't one logged in, create a
                    new user
                 */
                FirebaseUtility.getOrMakeUser(userModel) {
                    FirebaseUtility.firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN){
                        param(FirebaseAnalytics.Param.ITEM_ID, Firebase.auth.uid!!)
                        param(FirebaseAnalytics.Param.ITEM_NAME, "User ${userModel.user!!.username} logged on")
                    }

                    sharedPreferences.edit().putString(Constants.THEME_KEY, userModel.userTheme.value)
                        .apply()

                    if (userModel.hasCompletedSetup()) {
                        val id = navController.currentDestination!!.route

                        Log.d(Constants.SETUP, "Authenticating")

                        // If not on the Firebase provided AuthUI Screen go to the home screen
                        if (id == TodoViews.Splash.route) {

                            // Add Firebase List listener
                            FirebaseUtility.addListListener(listViewModel.listEvent)

                            FirebaseUtility.addItemListener(itemViewModel.itemsEvent)

                            Log.d(
                                Constants.SETUP,
                                "Navigating to Home Page: ${TodoViews.Home.route}"
                            )
                            navController.navigate(TodoViews.Home.route)
                        }
                    } else {
                        if (!userModel.selectingImage) {
                            Log.d(
                                Constants.SETUP,
                                "Navigating to UserName Setup: ${TodoViews.UserNameSetup.route}"
                            )
                            navController.navigate(TodoViews.UserNameSetup.route)
                        } else {
                            userModel.selectingImage = false
                            Log.d(
                                Constants.SETUP,
                                "Navigating to ProfileImage: ${TodoViews.ProfileImage.route}"
                            )
                            navController.navigate(TodoViews.ProfileImage.route)
                        }
                    }
                }
            }
        }
    }

    /**
     * Initializes and launches the Firebase AuthUI
     */
    private fun setupAuthUI() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        val signinIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setIsSmartLockEnabled(false)
            .setTheme(R.style.Theme_Todo)
            .build()
        signinLauncher.launch(signinIntent)
    }

    /**
     * Takes a uri and converts it to an image url
     */
    private fun addImageFromUri(uri: Uri?) {
        // Catch null uri
        Log.d(Constants.SETUP, "Trying to add new image")
        if (uri == null) {
            Log.e(Constants.SETUP, "URI is null. Not saving to storage")
            return
        }

        val stream = this.contentResolver.openInputStream(uri)

        // Catch null stream
        if (stream == null) {
            Log.e(Constants.SETUP, "Stream is null. Not saving to storage")
            return
        }

        // Add storage reference
        val imageStorageRef = Firebase.storage
            .reference
            .child("images")

        val imageId = kotlin.math.abs(Random.nextLong()).toString()
        imageStorageRef.child(imageId).putStream(stream)
            .continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                imageStorageRef.child(imageId).downloadUrl
            }
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    userModel.imageEvent.value = task.result.toString()

                    // Update the user in Firebase on the Dispatchers.IO thread
                    lifecycleScope.launch {
                        withContext(Dispatchers.IO) {
                            FirebaseUtility.updateUser(userModel)
                        }
                    }

                    Log.d(Constants.SETUP, "Got download uri: ${userModel.userImage}")
                } else {
                    Log.d(Constants.SETUP, "Failed to retrieve download uri")
                }
            }

    }

    /**
     * Check if which requested permissions have been granted
     *
     * @return Boolean - indicates if all permissions have been granted
     */
    private fun hasPermissions(): Boolean {
        return checkSelfPermission(READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(
            WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * Asks for permissions needed for the app
     */
    private fun askForPermissions() {
        activityResultLauncher.launch(
            arrayOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE)
        )
    }

    private val pickMediaActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        addImageFromUri(uri)
    }

    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                val permissionName = it.key
                val isGranted = it.value
                if (isGranted) {
                    Log.d(Constants.PERMISSIONS, "$permissionName :permission is granted")
                }
            }
        }

}