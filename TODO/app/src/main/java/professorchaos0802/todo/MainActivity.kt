package professorchaos0802.todo

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import professorchaos0802.todo.composeui.home.HomeScreenView
import professorchaos0802.todo.composeui.list.ListScreenView
import professorchaos0802.todo.composeui.profile.ProfileScreenView
import professorchaos0802.todo.composeui.profileimage.ProfileImage
import professorchaos0802.todo.composeui.splash.SplashScreenView
import professorchaos0802.todo.composeui.usercusotmization.UserCustomization
import professorchaos0802.todo.composeui.usernamesetup.UserNameSetupScreenView
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.navigation.TodoViews
import professorchaos0802.todo.theme.TodoTheme
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavHostController
    private lateinit var authListener: FirebaseAuth.AuthStateListener

    private val listViewModel: ListViewModel by viewModels()
    private val userModel: UserViewModel by viewModels()

    private val signinLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) {/* empty since the auth listener already responds */ }

    override fun onStart() {
        super.onStart()
        Firebase.auth.addAuthStateListener(authListener)
    }

    override fun onStop() {
        super.onStop()
        Firebase.auth.removeAuthStateListener(authListener)

        // Make sure there are no Firebase listeners remaining when the app stops
        listViewModel.subscriptions.forEach{ entry ->
            listViewModel.removeListener(entry.key)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!hasPermissions()) {
            askForPermissions()
        }

        initializeAuthListener()
        setupObservers()

        setContent {
            TodoTheme(
                color = if(userModel.user == null) "Blue" else userModel.user!!.theme
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
                                    userModel.updateName()
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
                        }

                        composable(route = TodoViews.Customization.route) {
                            UserCustomization(
                                userModel = userModel,
                                onNext = {
                                    userModel.update()
                                    Log.d(
                                        Constants.SETUP,
                                        "Navigating to ProfileImageView: ${TodoViews.ProfileImage.route}"
                                    )
                                    navController.navigate(TodoViews.ProfileImage.route)
                                },
                                onCancel = {
                                    Log.d(Constants.SETUP, "Logging out from UserCustomizationView")
                                    navController.navigate(TodoViews.Splash.route)
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
                                    userModel.update()
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
                            listViewModel.addListListener(Constants.listListenerId)
                            HomeScreenView(
                                userViewModel = userModel,
                                listViewModel = listViewModel,
                                onNavigateToList = { navController.navigate(TodoViews.List.route) }
                            )
                        }

                        composable(route = TodoViews.List.route){
                            ListScreenView(
                                userModel = userModel,
                                listModel = listViewModel
                            )
                        }

                        composable(route = TodoViews.Profile.route){
                            ProfileScreenView(
                                userModel = userModel
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

        listViewModel.myLists.observe(this){ allLists ->
            listViewModel.lists.value = allLists
        }

        listViewModel.currList.observe(this){ list ->
            listViewModel.currentList.value = list
        }

        listViewModel.currItems.observe(this){ allItems ->
            listViewModel.currentItems.value = allItems
        }

//        listViewModel.deleteLists.observe(this){
//            listViewModel.hasListsToDelete.value = it
//        }
    }

    private fun initializeAuthListener() {
        authListener = FirebaseAuth.AuthStateListener { auth: FirebaseAuth ->
            val user = auth.currentUser

            if (user == null) {
                setupAuthUI()
            } else {
                userModel.getOrMakeUser {
                    if (userModel.hasCompletedSetup()) {
                        val id = navController.currentDestination!!.route

                        Log.d(Constants.SETUP, "Authenticating")

                        // If not of the Firebase provided AuthUI Screen go to the home screen
                        if (id == TodoViews.Splash.route) {
                            Log.d(
                                Constants.SETUP,
                                "Navigating to Home Page: ${TodoViews.Home.route}"
                            )
                            navController.navigate(TodoViews.Home.route)
                        }
                    } else {
                        if(!userModel.selectingImage){
                            Log.d(
                                Constants.SETUP,
                                "Navigating to UserName Setup: ${TodoViews.UserNameSetup.route}"
                            )
                            navController.navigate(TodoViews.UserNameSetup.route)
                        }else{
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

    private fun setupAuthUI() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.EmailBuilder().build()
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
                    userModel.update()
                    Log.d(Constants.SETUP, "Got download uri: ${userModel.userImage}")
                } else {
                    Log.d(Constants.SETUP, "Failed to retrieve download uri")
                }
            }

    }

    private fun hasPermissions(): Boolean {
        return checkSelfPermission(READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(
            WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

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
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){ permissions ->
            permissions.entries.forEach{
                val permissionName = it.key
                val isGranted = it.value
                if (isGranted) {
                    Log.d(Constants.PERMISSIONS, "$permissionName :permission is granted")
                }
            }
        }

}