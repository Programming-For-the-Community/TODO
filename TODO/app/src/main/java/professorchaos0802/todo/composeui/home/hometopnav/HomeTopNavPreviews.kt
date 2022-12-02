package professorchaos0802.todo.composeui.home.hometopnav

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.theme.TodoTheme

@Preview(showBackground = true)
@Composable
fun LightBlueTopNavPreview(){
    TodoTheme(color = "Blue"){
        HomeTopNav(UserViewModel(), mutableListOf(), {}){}
    }
}

@Preview(showBackground = true)
@Composable
fun DarkBlueTopNavPreview(){
    TodoTheme(color = "Blue", darkTheme = true){
        HomeTopNav(UserViewModel(), mutableListOf(), {}){}
    }
}

@Preview(showBackground = true)
@Composable
fun LightGreenTopNavPreview(){
    TodoTheme(color = "Green"){
        HomeTopNav(UserViewModel(), mutableListOf(), {}){}
    }
}

@Preview(showBackground = true)
@Composable
fun DarkGreenTopNavPreview(){
    TodoTheme(color = "Green", darkTheme = true){
        HomeTopNav(UserViewModel(), mutableListOf(), {}){}
    }
}

@Preview(showBackground = true)
@Composable
fun LightRedTopNavPreview(){
    TodoTheme(color = "Red"){
        HomeTopNav(UserViewModel(), mutableListOf(), {}){}
    }
}

@Preview(showBackground = true)
@Composable
fun DarkRedTopNavPreview(){
    TodoTheme(color = "Red", darkTheme = true){
        HomeTopNav(UserViewModel(), mutableListOf(), {}){}
    }
}

@Preview(showBackground = true)
@Composable
fun LightOrangeTopNavPreview(){
    TodoTheme(color = "Orange"){
        HomeTopNav(UserViewModel(), mutableListOf(), {}){}
    }
}

@Preview(showBackground = true)
@Composable
fun DarkOrangeTopNavPreview(){
    TodoTheme(color = "Orange", darkTheme = true){
        HomeTopNav(UserViewModel(), mutableListOf(), {}){}
    }
}

@Preview(showBackground = true)
@Composable
fun LightPinkTopNavPreview(){
    TodoTheme(color = "Pink"){
        HomeTopNav(UserViewModel(), mutableListOf(), {}){}
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPinkTopNavPreview(){
    TodoTheme(color = "Pink", darkTheme = true){
        HomeTopNav(UserViewModel(), mutableListOf(), {}){}
    }
}

@Preview(showBackground = true)
@Composable
fun LightPurpleTopNavPreview(){
    TodoTheme(color = "Purple"){
        HomeTopNav(UserViewModel(), mutableListOf(), {}){}
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPurpleTopNavPreview(){
    TodoTheme(color = "Purple", darkTheme = true){
        HomeTopNav(UserViewModel(), mutableListOf(), {}){}
    }
}