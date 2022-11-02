package professorchaos0802.todo.models

import android.net.Uri
import android.os.Environment
import android.text.Editable
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.BuildConfig
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import professorchaos0802.todo.Constants
import professorchaos0802.todo.objects.MyList
import professorchaos0802.todo.objects.User
import java.io.File
import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class UserViewModel: ViewModel() {
    private var ref: DocumentReference? = null
    var user: User? = null
    var editUser = false
    var newProfileImage = ""

    var imageStorageRef: StorageReference? = null

    val themeEvent = MutableLiveData("Blue")
    val theme: LiveData<String> = themeEvent
    var userTheme = mutableStateOf("Blue")

    val nameEvent = MutableLiveData("")
    val name: LiveData<String> = nameEvent
    var userName = mutableStateOf("")


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
            ref!!.get().addOnSuccessListener {
                if(it.exists()){
                    user = it.toObject(User::class.java)
                    nameEvent.value = user!!.username
                }else{
                    user = User(username= Firebase.auth.currentUser!!.displayName!!)
                    nameEvent.value = user!!.username
                    ref!!.set(user!!)
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
     * Gets a temporary file uri before launcing the image selection
     */
    fun getTmpFileUri(fragment: Fragment): Uri {
        val storageDir: File = fragment.requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val tmpFile = File.createTempFile("JPEG_${timeStamp}_", ".png", storageDir).apply {
            createNewFile()
            deleteOnExit()
        }

        return FileProvider.getUriForFile(
            fragment.requireContext(),
            "${BuildConfig.APPLICATION_ID}.provider",
            tmpFile
        )
    }

    /**
     * Takes a uri and converts it to an image url
     */
    fun addImageFromUri(fragment: Fragment, uri: Uri?){
        // Catch null uri
        Log.d(Constants.USER_UPDATE, "Trying to add new image")
        if(uri == null){
            Log.e(Constants.USER_UPDATE, "URI is null. Not saving to storage")
            return
        }

        val stream = fragment.requireActivity().contentResolver.openInputStream(uri)

        // Catch null stream
        if(stream == null){
            Log.e(Constants.USER_UPDATE, "Stream is null. Not saving to storage")
            return
        }

        // Add storage reference
        Firebase.storage
            .reference
            .child("images")

        val imageId = kotlin.math.abs(Random.nextLong()).toString()
        imageStorageRef!!.child(imageId).putStream(stream)
            .continueWithTask{task ->
                if(!task.isSuccessful){
                    task.exception?.let{
                        throw it
                    }
                }
                imageStorageRef!!.child(imageId).downloadUrl
            }
            .addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    newProfileImage = task.result.toString()
                    user!!.img = newProfileImage
                    Log.d(Constants.USER_UPDATE, "Got download uri: $newProfileImage")
                }else{
                    Log.d(Constants.USER_UPDATE, "Failed to retrieve download uri")
                }
            }

    }

    /**
     * Updates the currently logged in user
     */
    fun update(){
        ref = Firebase.firestore.collection(User.COLLECTION_PATH).document(Firebase.auth.uid!!)
        user!!.theme = userTheme.value

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