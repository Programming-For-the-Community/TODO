package professorchaos0802.todo.composeui.home.shareDialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.objects.MyList
import professorchaos0802.todo.theme.TodoTheme

@Preview
@Composable
fun ShareDialogPreview(){
    val showDialog = remember{ mutableStateOf(true) }
    val listModel = ListViewModel()
    val userModel = UserViewModel()
    val list = mutableListOf<MyList>()
    for(i in 0..12){
        list.add(MyList(owner = "JDoe", title = "Title"))
    }
    listModel.lists.value = list

    TodoTheme(
        color = "Blue"
    ) {
        ShareDialog(userModel = userModel, listModel = listModel, showDialog = showDialog)
    }
}