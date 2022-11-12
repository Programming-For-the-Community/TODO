package professorchaos0802.todo.composeui.home.homescreenfab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import professorchaos0802.todo.theme.TodoTheme

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