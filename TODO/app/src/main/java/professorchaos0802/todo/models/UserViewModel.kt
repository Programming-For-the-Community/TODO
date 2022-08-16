package professorchaos0802.todo.models

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import professorchaos0802.todo.objects.User

class UserViewModel: ViewModel() {
    var ref = Firebase.firestore.collection(User.COLLECTION_PATH).document(Firebase.auth.uid!!)
    var user: User? = null
    var editUser = false

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

    fun hasCompletedSetup(): Boolean = user?.hasCompletedSetup ?: false

    fun update(){
        ref = Firebase.firestore.collection(User.COLLECTION_PATH).document(Firebase.auth.uid!!)

        if(user != null){
            with(user!!){
                ref.set(this)
            }
        }
    }
}