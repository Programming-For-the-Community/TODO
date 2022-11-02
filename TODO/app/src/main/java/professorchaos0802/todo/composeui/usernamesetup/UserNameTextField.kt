package professorchaos0802.todo.composeui.usernamesetup

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.objects.User
import professorchaos0802.todo.theme.TodoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserNameTextField(userModel: UserViewModel, onNext: () -> Unit) {

    TextField(
        value = TextFieldValue(userModel.userName.value),
        placeholder = {
            Text(
                text = userModel.userName.value,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primaryContainer
            )
        },
        label = {
            Text(
                text = "Enter your username",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondary
            )
        },
        textStyle = MaterialTheme.typography.labelLarge,
        singleLine = true,
        shape = RoundedCornerShape(25),
        onValueChange = { newName: TextFieldValue ->
            userModel.nameEvent.value = newName.text
        },
        colors = ExposedDropdownMenuDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.primary,
            textColor = MaterialTheme.colorScheme.onPrimary,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.background
        ),
        modifier = Modifier
            .fillMaxWidth()
    )
}

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