package professorchaos0802.todo.composeui.usernamesetup.usernametextfield

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import professorchaos0802.todo.theme.TodoTheme

@Preview(showBackground = true)
@Composable
fun BlueUserNameTextFieldPreview() {
    TodoTheme(
        color = "Blue"
    ) {
        UserNameTextField(viewModel(), {})
    }
}

@Preview(showBackground = true)
@Composable
fun GreenUserNameTextFieldPreview() {
    TodoTheme(
        color = "Green"
    ) {
        UserNameTextField(viewModel(), {})
    }
}

@Preview(showBackground = true)
@Composable
fun RedUserNameTextFieldPreview() {
    TodoTheme(
        color = "Red"
    ) {
        UserNameTextField(viewModel(), {})
    }
}

@Preview(showBackground = true)
@Composable
fun OrangeUserNameTextFieldPreview() {
    TodoTheme(
        color = "Orange"
    ) {
        UserNameTextField(viewModel(), {})
    }
}

@Preview(showBackground = true)
@Composable
fun PinkUserNameTextFieldPreview() {
    TodoTheme(
        color = "Pink"
    ) {
        UserNameTextField(viewModel(), {})
    }
}

@Preview(showBackground = true)
@Composable
fun PurpleUserNameTextFieldPreview() {
    TodoTheme(
        color = "Purple"
    ) {
        UserNameTextField(viewModel(), {})
    }
}