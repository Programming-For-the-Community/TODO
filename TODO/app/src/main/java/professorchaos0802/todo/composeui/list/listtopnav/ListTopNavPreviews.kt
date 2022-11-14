package professorchaos0802.todo.composeui.list.listtopnav

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import professorchaos0802.todo.theme.TodoTheme

@Preview
@Composable
fun BlueListTopNavPreview(){
    Column {
        TodoTheme(
            color = "Blue",
            darkTheme = true
        ) {
            ListTopNav {}
        }

        TodoTheme(
            color = "Blue",
            darkTheme = false
        ) {
            ListTopNav {}
        }
    }
}

@Preview
@Composable
fun GreenListTopNavPreview(){
    Column {
        TodoTheme(
            color = "Green",
            darkTheme = true
        ) {
            ListTopNav {}
        }

        TodoTheme(
            color = "Green",
            darkTheme = false
        ) {
            ListTopNav {}
        }
    }
}

@Preview
@Composable
fun RedListTopNavPreview(){
    Column {
        TodoTheme(
            color = "Red",
            darkTheme = true
        ) {
            ListTopNav {}
        }

        TodoTheme(
            color = "Red",
            darkTheme = false
        ) {
            ListTopNav {}
        }
    }
}

@Preview
@Composable
fun OrangeListTopNavPreview(){
    Column {
        TodoTheme(
            color = "Orange",
            darkTheme = true
        ) {
            ListTopNav {}
        }

        TodoTheme(
            color = "Orange",
            darkTheme = false
        ) {
            ListTopNav {}
        }
    }
}

@Preview
@Composable
fun PinkListTopNavPreview(){
    Column {
        TodoTheme(
            color = "Pink",
            darkTheme = true
        ) {
            ListTopNav {}
        }

        TodoTheme(
            color = "Pink",
            darkTheme = false
        ) {
            ListTopNav {}
        }
    }
}

@Preview
@Composable
fun PurpleListTopNavPreview(){
    Column {
        TodoTheme(
            color = "Purple",
            darkTheme = true
        ) {
            ListTopNav {}
        }

        TodoTheme(
            color = "Purple",
            darkTheme = false
        ) {
            ListTopNav {}
        }
    }
}