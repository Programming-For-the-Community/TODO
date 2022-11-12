package professorchaos0802.todo.composeui.usernamesetup.usernametextfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import professorchaos0802.todo.models.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserNameTextField(userModel: UserViewModel, onNext: () -> Unit) {
    TextField(
        value = TextFieldValue(
            text = userModel.userName.value,
            selection = TextRange(userModel.userName.value.length) // Set the cursor position to the end of the name
        ),
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