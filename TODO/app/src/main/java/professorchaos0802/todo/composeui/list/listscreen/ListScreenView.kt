package professorchaos0802.todo.composeui.list.listscreen

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import professorchaos0802.todo.Constants
import professorchaos0802.todo.composeui.list.listcontent.listcontentscreen.ListCanEdit
import professorchaos0802.todo.composeui.list.listtopnav.ListTopNav
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.theme.TodoTheme
import professorchaos0802.todo.utilities.FirebaseUtility

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreenView(
    userModel: UserViewModel = viewModel(),
    listModel: ListViewModel = viewModel(),
    onBackClick: () -> Unit
) {
    val scope = rememberCoroutineScope()

    TodoTheme(color = userModel.userTheme.value) {
        Scaffold(
            topBar = {
                ListTopNav(
                    onBackClick = {
                        // Update the currentList and adds back the listener for all lists on the Dispatchers.IO thread
                        scope.launch {
                            withContext(Dispatchers.IO) {
                                FirebaseUtility.updateCurrentList(
                                    currentList = listModel.currentList.value!!,
                                    currentTitle = listModel.currentListTitle.value
                                )

                                FirebaseUtility.addListListener(
                                    Constants.listListenerId,
                                    listModel.listEvent
                                )

                                listModel.currentList.value?.let { FirebaseUtility.removeListener(it.id) }
                            }
                        }
                        onBackClick()
                    }
                )
            }
        ) {
            listModel.currentList.value?.canView?.let { list ->
                ListCanEdit(
                    model = listModel,
                    readOnly = list.contains(userModel.userName.value),
                    updateTitle = {}
                )
            }
        }
    }
}