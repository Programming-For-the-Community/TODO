package professorchaos0802.todo.composeui.list.listcontent.listcontentscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import professorchaos0802.todo.composeui.list.listcontent.listtitle.ListTitle
import professorchaos0802.todo.composeui.list.listcontent.newItem.NewItem
import professorchaos0802.todo.models.ItemViewModel
import professorchaos0802.todo.models.ListViewModel

@Composable
fun ListContent(listModel: ListViewModel, itemModel: ItemViewModel, user: String, readOnly: Boolean){

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.tertiaryContainer
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp)
        ) {
            ListTitle(listModel, readOnly)
        }

        if(!readOnly){
            NewItem(itemModel, listModel.currentList.value!!.id, user)
        }
    }
}