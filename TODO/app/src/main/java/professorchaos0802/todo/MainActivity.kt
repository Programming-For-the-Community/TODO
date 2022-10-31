package professorchaos0802.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth
import com.firebase.ui.auth.AuthUI
import professorchaos0802.todo.composeui.home.HomeScreenView
import professorchaos0802.todo.composeui.splash.SplashScreenView
import professorchaos0802.todo.composeui.usercusotmization.UserCustomization
import professorchaos0802.todo.composeui.usernamesetup.UserNameSetupScreenView
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.navigation.TodoViews
import professorchaos0802.todo.theme.TodoTheme

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavHostController
    private lateinit var authListener: FirebaseAuth.AuthStateListener

    private val listViewModel: ListViewModel by viewModels()
    private val userModel: UserViewModel by viewModels()


    private val signinLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ){/* empty since the auth listener already responds */}

    override fun onStart(){
        super.onStart()
        Firebase.auth.addAuthStateListener(authListener)
    }

    override fun onStop(){
        super.onStop()
        Firebase.auth.removeAuthStateListener(authListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            TodoTheme{
                Surface(modifier = Modifier.fillMaxSize()){
                    navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = TodoViews.Splash.route
                    ){

                        composable(route = TodoViews.Splash.route){
                            SplashScreenView(
                                userViewModel = userModel
                            )
                        }

                        composable(route = TodoViews.UserNameSetup.route){
                            UserNameSetupScreenView(
                                userModel = userModel,
                                onNext = {navController.navigate(TodoViews.Customization.route)},
                                onCancel = {navController.navigate(TodoViews.Splash.route)}
                            )
                        }

                        composable(route = TodoViews.Customization.route){
                            UserCustomization(
                                userModel = userModel,
                                onNext = {navController.navigate(TodoViews.ProfileImage.route)},
                                onCancel = {navController.navigate(TodoViews.Splash.route)}
                            )
                        }

                        composable(route = TodoViews.Home.route){
                            HomeScreenView(
                                userViewModel = userModel,
                                listViewModel = listViewModel
                            )
                        }
                    }
                }
            }
        }

        initializeAuthListener()

    }

    private fun initializeAuthListener() {
        authListener = FirebaseAuth.AuthStateListener { auth: FirebaseAuth ->
            val user = auth.currentUser

            if(user == null){
                setupAuthUI()
            }else{
                with(user){this
                    userModel.getOrMakeUser{
                        if(userModel.hasCompletedSetup()){
                            val id = navController.currentDestination!!.route

                            Log.d(Constants.SETUP, "Authenticating")

                            // If not of the Firebase provided AuthUI Screen go to the home screen
                            if(id == TodoViews.Splash.route){
                                Log.d(Constants.SETUP, "Navigating to Home Page: ${TodoViews.Home.route}")
                                navController.navigate(TodoViews.Home.route)
                            }
                        }else{
                            Log.d(Constants.SETUP, "Navigating to UserName Setup: ${TodoViews.UserNameSetup.route}")
                            navController.navigate(TodoViews.UserNameSetup.route)
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
}