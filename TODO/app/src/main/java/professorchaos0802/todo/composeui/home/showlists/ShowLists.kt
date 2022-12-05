package professorchaos0802.todo.composeui.home.showlists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import professorchaos0802.todo.composeui.home.listcardview.ListCardView
import professorchaos0802.todo.models.ItemViewModel
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.objects.MyList
import professorchaos0802.todo.utilities.FirebaseUtility

/**
 * Shows a preview of all the lists available to the user in [HomeScreenView]
 *
 * @param listViewModel - [ListViewModel]: view model containing information on all the available lists
 * @param username - [String]: username of the logged in user
 */
@Composable
fun ShowLists(
    listViewModel: ListViewModel,
    itemModel: ItemViewModel,
    username: String,
    listsToDelete: MutableList<MyList>,
    onClick:() -> Unit
){
    val scope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()

    LazyColumn(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(bottom = 10.dp),
        state = scrollState,
        userScrollEnabled = true,
        modifier = Modifier
            .fillMaxSize(1F)
            .padding(
                top = 64.dp,
                start = 15.dp,
                end = 15.dp
            )
    ){
        itemsIndexed(listViewModel.lists.value){ _ , list ->
            if(list.owner == username ||
                list.canEdit.contains(username) ||
                list.canView.contains(username)) {

                ListCardView(
                    list = list,
                    items = itemModel.items.value.filter{ item ->
                        item.listId == list.id
                    },
                    username = username,
                    listsToDelete = listsToDelete,
                    onClick = {
                        listViewModel.updateCurrentList(list)

                        // Add Fireabse Listeners on Dispatchers.IO thread
                        scope.launch{
                            withContext(Dispatchers.IO){
                                FirebaseUtility.addCurrentListListener(list, listViewModel.currentListEvent)
                                FirebaseUtility.addCurrentListItemListener(list, itemModel.currentListItemsEvent)
                            }
                        }

                        onClick()
                    }
                )
            }
        }
    }
}