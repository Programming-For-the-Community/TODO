package professorchaos0802.todo.composeui.usercusotmization.usercustomizationradiobutton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

/**
 * Radio button used in [UserCustomization]
 *
 * @param text - [String]: text displayed next to radio button
 * @param selected - [Boolean]: boolean indicating if the radio button has been selected
 * @param onClick - [Unit]: Lambda function executed when the button is pressed
 */
@Composable
fun UserCustomizationRadioButton(text: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                unselectedColor = MaterialTheme.colorScheme.primary,
                selectedColor = MaterialTheme.colorScheme.tertiary
            ),
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}
