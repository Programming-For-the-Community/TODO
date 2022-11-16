package professorchaos0802.todo.composeui.home.showlists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import professorchaos0802.todo.composeui.home.listcardview.ListCardView
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.utilities.FirebaseUtility

/**
 * Shows a preview of all the lists available to the user in [HomeScreenView]
 *
 * @param listViewModel - [ListViewModel]: view model containing information on all the available lists
 * @param username - [String]: username of the logged in user
 */
@Composable
fun ShowLists(listViewModel: ListViewModel, username: String, onClick:() -> Unit){
    val scope = rememberCoroutineScope()

    LazyColumn(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        userScrollEnabled = true,
        modifier = Modifier
            .fillMaxSize(1F)
            .padding(
                top = 75.dp,
                bottom = 25.dp,
                start = 25.dp,
                end = 25.dp
            )
    ){
        items(items = listViewModel.lists.value){ list ->
            if(list.owner == username ||
                list.canEdit.contains(username) ||
                list.canView.contains(username)) {

                ListCardView(
                    list = list,
                    onClick = {
                        scope.launch{
                            withContext(Dispatchers.IO){
                                FirebaseUtility.addCurrentListListener(list, listViewModel.currentListEvent)
                                FirebaseUtility.addItemListener(list, listViewModel.itemEvent)
                            }
                        }
                        onClick()
                    }
                )
            }
        }
    }
}