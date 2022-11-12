package professorchaos0802.todo.composeui.usernamesetup.usernamesetupcontent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import professorchaos0802.todo.composeui.repeatedcomponents.progressionbuttons.ProgressionButtons
import professorchaos0802.todo.composeui.usernamesetup.usernametextfield.UserNameTextField
import professorchaos0802.todo.models.UserViewModel

/**
 * Full Content Display for [UserNameSetupScreenView]. Makes use of [ProgressionButtons] and [UserNameTextField]
 *
 * @param userModel - [UserViewModel]: contains user information
 * @param onNext - [Unit]: Lambda function called passed into [ProgressionButtons] for onNext
 * @param onCancel - [Unit]: Lambda function passed into [ProgressionButtons] for onCancel
 */
@Composable
fun UserNameSetupContent(userModel: UserViewModel, onNext: () -> Unit, onCancel: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 25.dp, end = 25.dp)
    ) {
        UserNameTextField(userModel, onNext)
        ProgressionButtons(onNext, onCancel) // Progression Buttons
    }
}