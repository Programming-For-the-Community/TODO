package professorchaos0802.todo.composeui.repeatedcomponents.progressionbuttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import professorchaos0802.todo.composeui.repeatedcomponents.ProgressionButton
import professorchaos0802.todo.models.UserViewModel

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
