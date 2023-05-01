package professorchaos0802.todo.composeui.home.shareDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import professorchaos0802.todo.Constants
import professorchaos0802.todo.R
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.objects.MyList
import professorchaos0802.todo.utilities.FirebaseUtility

@Composable
fun ShareDialog(userModel: UserViewModel, listModel: ListViewModel, user: String, showDialog: MutableState<Boolean>) {
    val context = LocalContext.current
    val darkTheme = isSystemInDarkTheme()
    val scope = rememberCoroutineScope()
    val selectLists = remember{ mutableStateOf(true) }
    val listsToShare = mutableListOf<MyList>()
    val usersToShareWith = mutableMapOf<String, String>()
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
                    FirebaseUtility.addPublicUsersListener(userModel.publicUserEvent)
                    SelectUsers(userModel.publicUsers.value, usersToShareWith, darkTheme)
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
                       showDialog.value = false

                       // Add Users to the chosen lists with the given permissions on the Dispatchers.IO
                       // thread
                       scope.launch{
                           withContext(Dispatchers.IO){
                               listsToShare.forEach{list ->
                                   usersToShareWith.forEach{ userAndPermission ->
                                       when(userAndPermission.value){
                                           "Can Edit" -> list.canEdit.add(userAndPermission.key)
                                           else -> list.canView.add(userAndPermission.key)
                                       }
                                   }

                                   FirebaseUtility.updateList(list)
                               }

                               // Remove Public Users Listener once all the lists are updated
                               FirebaseUtility.removeListener(Constants.publicUserListenerId)
                           }
                       }

                   }
               )
           }
        }
    }
}