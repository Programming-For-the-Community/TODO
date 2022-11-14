package professorchaos0802.todo.models

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import professorchaos0802.todo.Constants
import professorchaos0802.todo.objects.User

class UserViewModel: ViewModel() {
    private var ref: DocumentReference? = null
    var user: User? = null
    var selectingImage = false

    val themeEvent = MutableLiveData("Blue")
    val theme: LiveData<String> = themeEvent
    var userTheme = mutableStateOf("Blue")

    val nameEvent = MutableLiveData("")
    val name: LiveData<String> = nameEvent
    var userName = mutableStateOf("")

    val imageEvent = MutableLiveData("")
    val image: LiveData<String> = imageEvent
    var userImage = mutableStateOf("")


    /**
     * If the current User exists in firestore, this method gets that user, otherwise it creates a new user
     */
    fun getOrMakeUser(sharedPreferences: SharedPreferences, observer: () -> Unit) {
        ref = Firebase.firestore.collection(User.COLLECTION_PATH).document(Firebase.auth.uid!!)

        if(user != null){
            // get
            observer()
        }else{
            // make
            ref!!.get().addOnSuccessListener {
                if(it.exists()){
                    user = it.toObject(User::class.java)
                    nameEvent.value = user!!.username
                    themeEvent.value = user!!.theme
                    imageEvent.value = user!!.img
                }else{
                    user = User(username= Firebase.auth.currentUser!!.displayName!!)
                    nameEvent.value = user!!.username
                    themeEvent.value = user!!.theme
                    imageEvent.value = user!!.img
                    ref!!.set(user!!)
                }
                sharedPreferences.edit().putString(Constants.THEME_KEY, userTheme.value).apply()

                observer()
            }
        }
    }

    /**
     * Returns whether or not the user has completed setup
     */
    fun hasCompletedSetup(): Boolean = user?.hasCompletedSetup ?: false

    /**
     * Updates the currently logged in user
     */
    fun update(){
        ref = Firebase.firestore.collection(User.COLLECTION_PATH).document(Firebase.auth.uid!!)
        user!!.theme = userTheme.value
        user!!.img = userImage.value

        if(user != null){
            with(user!!){
                ref!!.set(this)
            }
        }
    }

    /**
     * Update the username of the currently logged in user
     */
    fun updateName(){
        ref = Firebase.firestore.collection(User.COLLECTION_PATH).document(Firebase.auth.uid!!)

        if(user != null){
            with(user!!){
                username = userName.value
                ref!!.set(this)
            }
        }
    }
}