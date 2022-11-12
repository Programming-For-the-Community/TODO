package professorchaos0802.todo.composeui.usercusotmization.themeselectiondropdown

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.theme.TodoTheme


@Preview(showBackground = true)
@Composable
fun BlueThemeSelectionDropdownPreview() {
    TodoTheme(color = "Blue") {
        ThemeSelectionDropdown(UserViewModel())
    }
}

@Preview(showBackground = true)
@Composable
fun GreenThemeSelectionDropdownPreview() {
    TodoTheme(color = "Green") {
        ThemeSelectionDropdown(UserViewModel())
    }
}

@Preview(showBackground = true)
@Composable
fun RedThemeSelectionDropdownPreview() {
    TodoTheme(color = "Red") {
        ThemeSelectionDropdown(UserViewModel())
    }
}

@Preview(showBackground = true)
@Composable
fun OrangeThemeSelectionDropdownPreview() {
    TodoTheme(color = "Orange") {
        ThemeSelectionDropdown(UserViewModel())
    }
}

@Preview(showBackground = true)
@Composable
fun PinkThemeSelectionDropdownPreview() {
    TodoTheme(color = "Pink") {
        ThemeSelectionDropdown(UserViewModel())
    }
}

@Preview(showBackground = true)
@Composable
fun PurpleThemeSelectionDropdownPreview() {
    TodoTheme(color = "Purple") {
        ThemeSelectionDropdown(UserViewModel())
    }
}