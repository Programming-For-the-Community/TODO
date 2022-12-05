package professorchaos0802.todo.composeui.usernamesetup.usernametextfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import professorchaos0802.todo.models.UserViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun UserNameTextField(userModel: UserViewModel, showErrorSnackbar:() -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    TextField(
        value = userModel.userName.value,
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
        onValueChange = { newName ->
            userModel.nameEvent.value = newName
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                keyboardController?.hide()
                showErrorSnackbar()
            }
        ),
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
            .focusRequester(focusRequester)
            .fillMaxWidth()
            .onKeyEvent { event ->
                if (event.key == Key.Enter) {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                    userModel.nameEvent.value = userModel.userName.value.dropLast(1)
                    showErrorSnackbar()
                }

                false
            }
    )
}