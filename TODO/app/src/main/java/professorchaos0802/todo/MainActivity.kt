package professorchaos0802.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.firebase.ui.auth.AuthUI
import com.google.android.material.navigation.NavigationView
import io.grpc.InternalChannelz.id
import professorchaos0802.todo.databinding.ActivityMainBinding
import professorchaos0802.todo.models.UserViewModel

private lateinit var appBarConfiguration: AppBarConfiguration
private lateinit var navController: NavController
private lateinit var binding: ActivityMainBinding
private lateinit var authListener: FirebaseAuth.AuthStateListener


private val signinLauncher = registerForActivityResult(
    FirebaseAuthUIActivityResultContract()
){/* empty since the auth listener already responds */}

class MainActivity : AppCompatActivity() {

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
        initializeAuthListener()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun initializeAuthListener() {
        authListener = FirebaseAuth.AuthStateListener { auth: FirebaseAuth ->
            val user = auth.currentUser

            if(user == null){
                setupAuthUI()
            }else{
                with(user){this
                    val userModel = ViewModelProvider(this@MainActivity).get(UserViewModel::class.java)
                    userModel.getOrMakeUser{
                        if(userModel.hasCompletedSetup()){
                            val id = findNavController(R.id.nav_host_fragment_content_main).currentDestination!!.id

                            Log.d(Constants.SETUP, "Authenticating")
                        }
                    }
                }
            }
        }
    }

    private fun setupAuthUI() {
        TODO("Not yet implemented")
    }
}