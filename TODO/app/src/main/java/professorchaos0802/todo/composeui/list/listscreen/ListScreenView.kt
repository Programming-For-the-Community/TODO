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
import professorchaos0802.todo.composeui.list.listcontent.listcontentscreen.ListContent
import professorchaos0802.todo.composeui.list.listtopnav.ListTopNav
import professorchaos0802.todo.models.ItemViewModel
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
    itemModel: ItemViewModel = viewModel(),
    onBackClick: () -> Unit
) {
    val scope = rememberCoroutineScope()

    TodoTheme(color = userModel.userTheme.value) {
        Scaffold(
            topBar = {
                listModel.currentList.value?.canView?.contains(userModel.userName.value)?.let { readOnly ->
                    ListTopNav(
                        listModel = listModel,
                        readOnly = readOnly,
                        onBackClick = {
                            // Update the currentList and adds back the listener for all lists on the Dispatchers.IO thread
                            scope.launch {
                                withContext(Dispatchers.IO) {
                                    FirebaseUtility.updateCurrentList(
                                        currentList = listModel.currentList.value!!,
                                        currentTitle = listModel.currentListTitle.value
                                    )


                                    // Remove listeners from list screen
                                    FirebaseUtility.removeListener(listModel.currentList.value!!.id)
                                    FirebaseUtility.removeListener(listModel.currentList.value!!.id + Constants.itemListenerId)

                                    listModel.currentList.value?.let { myList ->
                                        FirebaseUtility.removeListener(myList.id)
                                    }

                                    // Clear Current List values
                                    listModel.currentListEvent.postValue(null)
                                    itemModel.currentListItemsEvent.postValue(listOf())
                                }
                            }

                            onBackClick()
                        }
                    )
                }
            }
        ) {
            listModel.currentList.value?.canView?.let { list ->
                ListContent(
                    listModel = listModel,
                    itemModel = itemModel,
                    user = userModel.userName.value,
                    readOnly = list.contains(userModel.userName.value)
                )
            }
        }
    }
}