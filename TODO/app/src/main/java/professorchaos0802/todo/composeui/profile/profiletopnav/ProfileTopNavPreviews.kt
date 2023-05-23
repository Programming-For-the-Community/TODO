package professorchaos0802.todo.composeui.profile.profiletopnav

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.theme.TodoTheme

@Preview(showBackground = true)
@Composable
fun LightBlueTopNavPreview(){
    TodoTheme(color = "Blue"){
        ProfileTopNav(UserViewModel(),  {})
    }
}

@Preview(showBackground = true)
@Composable
fun DarkBlueTopNavPreview(){
    TodoTheme(color = "Blue", darkTheme = true){
        ProfileTopNav(UserViewModel(), {})
    }
}

@Preview(showBackground = true)
@Composable
fun LightGreenTopNavPreview(){
    TodoTheme(color = "Green"){
        ProfileTopNav(UserViewModel(), {})
    }
}

@Preview(showBackground = true)
@Composable
fun DarkGreenTopNavPreview(){
    TodoTheme(color = "Green", darkTheme = true){
        ProfileTopNav(UserViewModel(), {})
    }
}

@Preview(showBackground = true)
@Composable
fun LightRedTopNavPreview(){
    TodoTheme(color = "Red"){
        ProfileTopNav(UserViewModel(), {})
    }
}

@Preview(showBackground = true)
@Composable
fun DarkRedTopNavPreview(){
    TodoTheme(color = "Red", darkTheme = true){
        ProfileTopNav(UserViewModel(), {})
    }
}

@Preview(showBackground = true)
@Composable
fun LightOrangeTopNavPreview(){
    TodoTheme(color = "Orange"){
        ProfileTopNav(UserViewModel(), {})
    }
}

@Preview(showBackground = true)
@Composable
fun DarkOrangeTopNavPreview(){
    TodoTheme(color = "Orange", darkTheme = true){
        ProfileTopNav(UserViewModel(), {})
    }
}

@Preview(showBackground = true)
@Composable
fun LightPinkTopNavPreview(){
    TodoTheme(color = "Pink"){
        ProfileTopNav(UserViewModel(), {})
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPinkTopNavPreview(){
    TodoTheme(color = "Pink", darkTheme = true){
        ProfileTopNav(UserViewModel(), {})
    }
}

@Preview(showBackground = true)
@Composable
fun LightPurpleTopNavPreview(){
    TodoTheme(color = "Purple"){
        ProfileTopNav(UserViewModel(), {})
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPurpleTopNavPreview(){
    TodoTheme(color = "Purple", darkTheme = true){
        ProfileTopNav(UserViewModel(), {})
    }
}