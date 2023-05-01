package professorchaos0802.todo.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import professorchaos0802.todo.objects.User

class UserViewModel: ViewModel() {
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

    val publicUserEvent: MutableLiveData<List<User>> = MutableLiveData<List<User>>()
    val myPublicUsers: LiveData<List<User>> = publicUserEvent
    val publicUsers = mutableStateOf(listOf<User>())

    /**
     * Returns whether or not the user has completed setup
     */
    fun hasCompletedSetup(): Boolean = user?.hasCompletedSetup ?: false

}