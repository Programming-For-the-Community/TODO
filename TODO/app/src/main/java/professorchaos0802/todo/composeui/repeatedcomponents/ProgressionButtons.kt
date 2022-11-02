package professorchaos0802.todo.composeui.repeatedcomponents

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.navigation.TodoViews
import professorchaos0802.todo.theme.TodoTheme

/**
 * Buttons used to progress through the UserSetup process using a [ProgressionButton]
 *
 * @param userModel - [UserViewModel]: view model containing user information
 * @param onNext - [Unit]: Lambda called when the "Next" button is pressed
 * @param onCancel - [Unit]: Lambda called when the "Cancel" button is pressed
 */
@Composable
fun ProgressionButtons(onNext: () -> Unit, onCancel: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp)
    ) {
        ProgressionButton(
            text = "Next",
            onClick = onNext
        )

        ProgressionButton(
            text = "Cancel",
            onClick = onCancel
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LightBlueProgressionButtonsPreview(){
    TodoTheme(
        color = "Blue",
        darkTheme = false
    ){
        ProgressionButtons({}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun LightGreenProgressionButtonsPreview(){
    TodoTheme(
        color = "Green",
        darkTheme = false
    ){
        ProgressionButtons({}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun LightRedProgressionButtonsPreview(){
    TodoTheme(
        color = "Red",
        darkTheme = false
    ){
        ProgressionButtons({}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun LightOrangeProgressionButtonsPreview(){
    TodoTheme(
        color = "Orange",
        darkTheme = false
    ){
        ProgressionButtons({}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun LightPinkProgressionButtonsPreview(){
    TodoTheme(
        color = "Pink",
        darkTheme = false
    ){
        ProgressionButtons({}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun LightPurpleProgressionButtonsPreview(){
    TodoTheme(
        color = "Purple",
        darkTheme = false
    ){
        ProgressionButtons({}, {})
    }
}
