package professorchaos0802.todo.composeui.repeatedcomponents.navdrawer

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import professorchaos0802.todo.composeui.repeatedcomponents.NavDrawer
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.theme.TodoTheme

@Preview(showBackground = true)
@Composable
fun NavDrawerPreview(){
    val model = UserViewModel()
    model.userName.value = "JDoe"

    TodoTheme(
        color = "Blue"
    ){
        NavDrawer(model, NavHostController(LocalContext.current), {}, {})
    }
}