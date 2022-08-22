package professorchaos0802.todo.models

import android.text.Editable
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import professorchaos0802.todo.objects.MyList
import professorchaos0802.todo.objects.User

class UserViewModel: ViewModel() {
    var ref = Firebase.firestore.collection(User.COLLECTION_PATH).document(Firebase.auth.uid!!)
    var user: User? = null
    var editUser = false
    var currentList = MyList()

    /**
     * If the current User exists in firestore, this method gets that user, otherwise it creates a new user
     */
    fun getOrMakeUser(observer: () -> Unit) {
        ref = Firebase.firestore.collection(User.COLLECTION_PATH).document(Firebase.auth.uid!!)

        if(user != null){
            // get
            observer()
        }else{
            // make
            ref.get().addOnSuccessListener {
                if(it.exists()){
                    user = it.toObject(User::class.java)
                }else{
                    user = User(username= Firebase.auth.currentUser!!.displayName!!)
                    ref.set(user!!)
                }

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

        if(user != null){
            with(user!!){
                ref.set(this)
            }
        }
    }

    /**
     * Update the username of the currently logged in user
     */
    fun updateName(newName: String?){
        ref = Firebase.firestore.collection(User.COLLECTION_PATH).document(Firebase.auth.uid!!)

        if(user != null && newName != null){
            with(user!!){
                username = newName
                ref.set(this)
            }
        }
    }
}