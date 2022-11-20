package professorchaos0802.todo.composeui.list.listtopnav

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.theme.TodoTheme

@Preview
@Composable
fun BlueListTopNavPreview(){
    Column {
        TodoTheme(
            color = "Blue",
            darkTheme = true
        ) {
            ListTopNav(ListViewModel(),  true, {})
        }

        TodoTheme(
            color = "Blue",
            darkTheme = false
        ) {
            ListTopNav(ListViewModel(),  true, {})
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
            ListTopNav(ListViewModel(),  true, {})
        }

        TodoTheme(
            color = "Green",
            darkTheme = false
        ) {
            ListTopNav(ListViewModel(),  true, {})
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
            ListTopNav(ListViewModel(),  true, {})
        }

        TodoTheme(
            color = "Red",
            darkTheme = false
        ) {
            ListTopNav(ListViewModel(),  true, {})
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
            ListTopNav(ListViewModel(),  true, {})
        }

        TodoTheme(
            color = "Orange",
            darkTheme = false
        ) {
            ListTopNav(ListViewModel(),  true, {})
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
            ListTopNav(ListViewModel(),  true, {})
        }

        TodoTheme(
            color = "Pink",
            darkTheme = false
        ) {
            ListTopNav(ListViewModel(),  true, {})
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
            ListTopNav(ListViewModel(),  true, {})
        }

        TodoTheme(
            color = "Purple",
            darkTheme = false
        ) {
            ListTopNav(ListViewModel(),  true, {})
        }
    }
}