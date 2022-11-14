package professorchaos0802.todo.composeui.repeatedcomponents.navdraweritem

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import professorchaos0802.todo.theme.TodoTheme

@Preview(showBackground = true)
@Composable
fun BlueNavDrawerItemPreview(){
    val text = "HOME"
    val icon = Icons.Filled.Home

    Column{
        TodoTheme(
            color = "Blue",
            darkTheme = false
        ) {
            NavDrawerItem(text, true, icon){}
            NavDrawerItem(text, false, icon){}
        }

        TodoTheme(
            color = "Blue",
            darkTheme = true
        ){
            NavDrawerItem(text = text, isCurrentView = true, icon = icon){}
            NavDrawerItem(text = text, isCurrentView = false, icon = icon){}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreenNavDrawerItemPreview(){
    val text = "HOME"
    val icon = Icons.Filled.Home

    Column{
        TodoTheme(
            color = "Green",
            darkTheme = false
        ) {
            NavDrawerItem(text, true, icon){}
            NavDrawerItem(text, false, icon){}
        }

        TodoTheme(
            color = "Green",
            darkTheme = true
        ){
            NavDrawerItem(text = text, isCurrentView = true, icon = icon){}
            NavDrawerItem(text = text, isCurrentView = false, icon = icon){}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RedNavDrawerItemPreview(){
    val text = "HOME"
    val icon = Icons.Filled.Home

    Column{
        TodoTheme(
            color = "Red",
            darkTheme = false
        ) {
            NavDrawerItem(text, true, icon){}
            NavDrawerItem(text, false, icon){}
        }

        TodoTheme(
            color = "Red",
            darkTheme = true
        ){
            NavDrawerItem(text = text, isCurrentView = true, icon = icon){}
            NavDrawerItem(text = text, isCurrentView = false, icon = icon){}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrangeNavDrawerItemPreview(){
    val text = "HOME"
    val icon = Icons.Filled.Home

    Column{
        TodoTheme(
            color = "Orange",
            darkTheme = false
        ) {
            NavDrawerItem(text, true, icon){}
            NavDrawerItem(text, false, icon){}
        }

        TodoTheme(
            color = "Orange",
            darkTheme = true
        ){
            NavDrawerItem(text = text, isCurrentView = true, icon = icon){}
            NavDrawerItem(text = text, isCurrentView = false, icon = icon){}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PinkNavDrawerItemPreview(){
    val text = "HOME"
    val icon = Icons.Filled.Home

    Column{
        TodoTheme(
            color = "Pink",
            darkTheme = false
        ) {
            NavDrawerItem(text, true, icon){}
            NavDrawerItem(text, false, icon){}
        }

        TodoTheme(
            color = "Pink",
            darkTheme = true
        ){
            NavDrawerItem(text = text, isCurrentView = true, icon = icon){}
            NavDrawerItem(text = text, isCurrentView = false, icon = icon){}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PurpleNavDrawerItemPreview(){
    val text = "HOME"
    val icon = Icons.Filled.Home

    Column{
        TodoTheme(
            color = "Purple",
            darkTheme = false
        ) {
            NavDrawerItem(text, true, icon){}
            NavDrawerItem(text, false, icon){}
        }

        TodoTheme(
            color = "Purple",
            darkTheme = true
        ){
            NavDrawerItem(text = text, isCurrentView = true, icon = icon){}
            NavDrawerItem(text = text, isCurrentView = false, icon = icon){}
        }
    }
}