package professorchaos0802.todo.composeui.home.homescreenfab

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
 * [FloatingActionButton] used to trigger an action from [HomeScreenView]
 *
 * @param icon - [ImageVector]: Icon to be displayed
 * @param onFabClick - [Unit]: Lambda function executed when the button is clicked
 */
@Composable
fun HomeScreenFab(icon: ImageVector, onFabClick: () -> Unit) {
    FloatingActionButton(
        onClick = onFabClick,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        shape = CircleShape,
        modifier = Modifier.size(50.dp)
    ) {

        Icon(
            imageVector = icon,
            contentDescription = "HomeScreenFabIcon",
            modifier = Modifier.size(40.dp)
        )
    }
}