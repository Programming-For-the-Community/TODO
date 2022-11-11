package professorchaos0802.todo.composeui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.objects.Item
import professorchaos0802.todo.objects.MyList
import professorchaos0802.todo.theme.TodoTheme

/**
 * Shows a preview of all the lists available to the user in [HomeScreenView]
 *
 * @param listViewModel - [ListViewModel]: view model containing information on all the available lists
 * @param username - [String]: username of the logged in user
 */
@Composable
fun ShowLists(listViewModel: ListViewModel, username: String, onClick:() -> Unit){
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
                    onClick = onClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BlueShowListsPreview(){
    val model = ListViewModel()
    val myLists = mutableListOf<MyList>()
    myLists.add(MyList("JDoe", "List1"))
    myLists.add(MyList("JDoe", "List2"))
    myLists.add(MyList("JDoe", "List3"))
    myLists.add(MyList("JDoe", "List4"))
    myLists.forEach { list ->
        for(i in 1..5){
            list.items.add(Item("JDoe", "Todo $i", false))
        }
        list.created = Timestamp.now()
    }
    model.lists.value = myLists
    
    TodoTheme(
        color = "Blue"
    ){
        ShowLists(model,"JDoe"){}
    }
}