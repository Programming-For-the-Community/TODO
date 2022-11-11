package professorchaos0802.todo.composeui.repeatedcomponents.progressionbutton

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import professorchaos0802.todo.composeui.repeatedcomponents.ProgressionButton
import professorchaos0802.todo.theme.TodoTheme

@Preview(showBackground = true)
@Composable
fun LightBlueProgressionButtonPreview(){
    TodoTheme(
        color = "Blue",
        darkTheme = false
    ){
        ProgressionButton("Text", {})
    }
}

@Preview(showBackground = true)
@Composable
fun LightGreenProgressionButtonPreview(){
    TodoTheme(
        color = "Green",
        darkTheme = false
    ){
        ProgressionButton("Text", {})
    }
}

@Preview(showBackground = true)
@Composable
fun LightRedProgressionButtonPreview(){
    TodoTheme(
        color = "Red",
        darkTheme = false
    ){
        ProgressionButton("Text", {})
    }
}

@Preview(showBackground = true)
@Composable
fun LightOrangeProgressionButtonPreview(){
    TodoTheme(
        color = "Orange",
        darkTheme = false
    ){
        ProgressionButton("Text", {})
    }
}

@Preview(showBackground = true)
@Composable
fun LightPinkProgressionButtonPreview(){
    TodoTheme(
        color = "Pink",
        darkTheme = false
    ){
        ProgressionButton("Text", {})
    }
}

@Preview(showBackground = true)
@Composable
fun LightPurpleProgressionButtonPreview(){
    TodoTheme(
        color = "Purple",
        darkTheme = false
    ){
        ProgressionButton("Text", {})
    }
}