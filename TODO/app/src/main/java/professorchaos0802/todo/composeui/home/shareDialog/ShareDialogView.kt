package professorchaos0802.todo.composeui.home.shareDialog

import android.widget.Toast
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
fun ShareDialog(userModel: UserViewModel, listModel: ListViewModel, showDialog: MutableState<Boolean>) {
    val context = LocalContext.current
    val darkTheme = isSystemInDarkTheme()
    val scope = rememberCoroutineScope()
    val user = userModel.userName.value // Current username
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
                    // Shows all the lists the current user owns or has edit rights to
                    SelectLists(listModel.lists.value.filter { it.canEdit.contains(user) || it.owner == user }, listsToShare, darkTheme)
                }else{
                    FirebaseUtility.addPublicUsersListener(userModel.publicUserEvent) // Attach a Firebase listener for the public users
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
                       if(listsToShare.isEmpty()){
                           Toast.makeText(context,
                               context.getString(R.string.no_lists_selected_warning),
                               Toast.LENGTH_SHORT)
                               .show()
                       }else{
                           selectLists.value = false
                           title.value = "Choose someone to share your list with:"
                       }
                   },
                   onShare = {
                       if(usersToShareWith.isEmpty()){
                           // Display warning toast if no users have been selected to share the list(s) with
                           Toast.makeText(context,
                               context.getString(if(listsToShare.size > 1) R.string.no_users_to_share_multiple_list_warning else R.string.no_users_to_share_single_list_warning),
                               Toast.LENGTH_SHORT)
                               .show()
                       }else{
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

                   }
               )
           }
        }
    }
}