package professorchaos0802.todo.composeui.usernamesetup.usernamesetupcontent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import professorchaos0802.todo.composeui.repeatedcomponents.progressionbuttons.ProgressionButtons
import professorchaos0802.todo.composeui.usernamesetup.usernametextfield.UserNameTextField
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.utilities.FirebaseUtility

/**
 * Full Content Display for [UserNameSetupScreenView]. Makes use of [ProgressionButtons] and [UserNameTextField]
 *
 * @param userModel - [UserViewModel]: contains user information
 * @param onNext - [Unit]: Lambda function called passed into [ProgressionButtons] for onNext
 * @param onCancel - [Unit]: Lambda function passed into [ProgressionButtons] for onCancel
 */
@Composable
fun UserNameSetupContent(userModel: UserViewModel, snackbarHostState: SnackbarHostState, onNext: () -> Unit, onCancel: () -> Unit) {
    val scope = rememberCoroutineScope()
    val allUsernames = FirebaseUtility.addUsernamesListener()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 25.dp, end = 25.dp)
    ) {
        UserNameTextField(
            userModel = userModel,
            showErrorSnackbar = {
                if (allUsernames.contains(userModel.userName.value)) {
                    scope.launch {
                        snackbarHostState.showSnackbar("This username is already taken")
                    }
                }
            }
        )
        ProgressionButtons(
            onNext = {
                if (allUsernames.contains(userModel.userName.value)) {
                    scope.launch {
                        snackbarHostState.showSnackbar("This username is already taken")
                    }
                } else {
                    if(userModel.userName.value == "JDoe"){
                        scope.launch {
                            snackbarHostState.showSnackbar("Please change your username")
                        }
                    }else {
                        allUsernames.add(userModel.userName.value)
                        FirebaseUtility.updateUsernames(allUsernames)
                        onNext()
                    }
                }
            },
            onCancel = onCancel
        ) // Progression Buttons
    }
}