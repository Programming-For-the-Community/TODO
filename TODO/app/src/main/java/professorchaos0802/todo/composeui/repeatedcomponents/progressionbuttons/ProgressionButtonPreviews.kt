package professorchaos0802.todo.composeui.repeatedcomponents.progressionbuttons

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import professorchaos0802.todo.theme.TodoTheme

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