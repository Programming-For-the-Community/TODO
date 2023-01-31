package professorchaos0802.todo.composeui.home.shareDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import professorchaos0802.todo.R
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.objects.MyList

@Composable
fun ShareDialog(listModel: ListViewModel, user: String, showDialog: MutableState<Boolean>) {
    val context = LocalContext.current
    val darkTheme = isSystemInDarkTheme()
    val selectLists = remember{ mutableStateOf(true) }
    val listsToShare = mutableListOf<MyList>()
    val title = remember{ mutableStateOf(context.getString(R.string.dialog_title))}

    Dialog(
        onDismissRequest = { showDialog.value = false },
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .height(300.dp)
                .background(color = Color.White)
        ) {
            Column(
                modifier = Modifier.height(235.dp)
            ){
                DialogTitle(darkTheme, title.value)

                if(selectLists.value){
                    SelectLists(listModel.lists.value.filter { it.canEdit.contains(user) || it.owner == user }, listsToShare, darkTheme)
                }else{
                    //TODO: Generate users list
                    SelectUsers(listOf<String>("Joe", "Joe", "Joe"), mutableMapOf<String, String>(), false)
                }
            }

           Column(
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally,
               modifier = Modifier.height(65.dp)
           ){
               DialogButtons(
                   selectLists = selectLists,
                   darkTheme = darkTheme,
                   onCancel = {
                       showDialog.value = false
                   },
                   onNext = {
                       selectLists.value = false
                       title.value = "Choose someone to share your list with:"
                   },
                   onShare = {
                       //TODO: Handle sharing
                       showDialog.value = false
                   }
               )
           }
        }
    }
}