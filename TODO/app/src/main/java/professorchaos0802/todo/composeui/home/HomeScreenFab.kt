package professorchaos0802.todo.composeui.home

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import professorchaos0802.todo.theme.TodoTheme

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
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary,
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

@Preview(showBackground = true)
@Composable
fun BlueNewListFabPreview() {
    TodoTheme(color = "Blue") {
        HomeScreenFab(Icons.Filled.Share) {}
    }
}

@Preview(showBackground = true)
@Composable
fun GreenNewListFabPreview() {
    TodoTheme(color = "Green") {
        HomeScreenFab(Icons.Filled.Share) {}
    }
}

@Preview(showBackground = true)
@Composable
fun RedNewListFabPreview() {
    TodoTheme(color = "Red") {
        HomeScreenFab(Icons.Filled.Share) {}
    }
}

@Preview(showBackground = true)
@Composable
fun OrangeNewListFabPreview() {
    TodoTheme(color = "Orange") {
        HomeScreenFab(Icons.Filled.Share) {}
    }
}

@Preview(showBackground = true)
@Composable
fun PinkNewListFabPreview() {
    TodoTheme(color = "Pink") {
        HomeScreenFab(Icons.Filled.Share) {}
    }
}

@Preview(showBackground = true)
@Composable
fun PurpleNewListFabPreview() {
    TodoTheme(color = "Purple") {
        HomeScreenFab(Icons.Filled.Share) {}
    }
}