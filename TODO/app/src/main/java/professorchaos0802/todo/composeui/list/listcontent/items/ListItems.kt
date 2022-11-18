package professorchaos0802.todo.composeui.list.listcontent.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import professorchaos0802.todo.models.ItemViewModel
import professorchaos0802.todo.utilities.FirebaseUtility

@Composable
fun ListItems(itemModel: ItemViewModel, readOnly: Boolean){
    val scope = rememberCoroutineScope()

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
                        color = MaterialTheme.colorScheme.tertiaryContainer
                    )
            ){
                Row{
                    TextField(
                        value = item.text,
                        leadingIcon = {
                            Checkbox(
                                checked = item.done,
                                onCheckedChange = {
                                    scope.launch{
                                        withContext(Dispatchers.IO){
                                            FirebaseUtility.updateItem(item, item.text, !item.done)
                                        }
                                    }
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                    uncheckedColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                    checkmarkColor = MaterialTheme.colorScheme.tertiaryContainer,
                                    disabledUncheckedColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                    disabledCheckedColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                    disabledIndeterminateColor = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            )
                        },
                        label = {},
                        placeholder = {},
                        onValueChange = {},
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
                            textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            leadingIconColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            focusedIndicatorColor = MaterialTheme.colorScheme.tertiaryContainer
                        ),
                    )
                }
            }
        }
    }

}