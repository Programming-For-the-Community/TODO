package professorchaos0802.todo.composeui.list.listcontent.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TextField
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import professorchaos0802.todo.models.ItemViewModel

@Composable
fun ListItems(itemModel: ItemViewModel, readOnly: Boolean){
    LazyColumn(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxWidth()
    ){
        items(items = itemModel.currentListItems.value){ item ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.tertiary
                    )
            ){
                Row{
                    TextField(
                        value = item.text,
                        leadingIcon = {
                            Checkbox(checked = item.done, onCheckedChange = {})
                        },
                        label = {},
                        placeholder = {},
                        onValueChange = {}
                    )
                }
            }
        }
    }

}