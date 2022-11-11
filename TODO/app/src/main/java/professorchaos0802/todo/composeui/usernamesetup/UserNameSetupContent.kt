package professorchaos0802.todo.composeui.usernamesetup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import professorchaos0802.todo.composeui.repeatedcomponents.progressionbuttons.ProgressionButtons
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.theme.TodoTheme

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

@Preview(showBackground = true)
@Composable
fun BlueUserNameSetupContentPreview(){
    TodoTheme(
        color = "Blue"
    ){
        UserNameSetupContent(viewModel(), {}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun GreenUserNameSetupContentPreview(){
    TodoTheme(
        color = "Green"
    ){
        UserNameSetupContent(viewModel(), {}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun RedUserNameSetupContentPreview(){
    TodoTheme(
        color = "Red"
    ){
        UserNameSetupContent(viewModel(), {}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun OrangeUserNameSetupContentPreview(){
    TodoTheme(
        color = "Orange"
    ){
        UserNameSetupContent(viewModel(), {}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun PinkUserNameSetupContentPreview(){
    TodoTheme(
        color = "Pink"
    ){
        UserNameSetupContent(viewModel(), {}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun PurpleUserNameSetupContentPreview(){
    TodoTheme(
        color = "Purple"
    ){
        UserNameSetupContent(viewModel(), {}, {})
    }
}