package professorchaos0802.todo.models

import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {
    fun getOrMakeUser(function: () -> Unit) {

    }

    fun hasCompletedSetup(): Boolean {
        return false
    }
}