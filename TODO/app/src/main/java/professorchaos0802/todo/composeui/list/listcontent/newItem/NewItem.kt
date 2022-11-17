package professorchaos0802.todo.composeui.list.listcontent.newItem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import professorchaos0802.todo.models.ItemViewModel
import professorchaos0802.todo.objects.Item
import professorchaos0802.todo.utilities.FirebaseUtility

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NewItem(itemModel: ItemViewModel, listId: String, user: String){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.secondary
            )
    ){
        val keyboardController = LocalSoftwareKeyboardController.current
        val focusRequester = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current
        val scope = rememberCoroutineScope()
        var showLabel by remember { mutableStateOf(false) }

        TextField(
            value = itemModel.itemText.value,
            textStyle = MaterialTheme.typography.bodyMedium,
            label = {
                if(showLabel){
                    Text(
                        text = "New Item",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            singleLine = true,
            onValueChange = { newItemText ->
                itemModel.itemText.value = newItemText
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    keyboardController?.hide()

                    val newItem = Item(
                        owner = user,
                        listId = listId,
                        text = itemModel.itemText.value,
                        done = false
                    )

                    // Add the current Item to Firebase on the Dispatchers.IO thread
                    scope.launch{
                        withContext(Dispatchers.IO){
                            FirebaseUtility.addItem(newItem)
                        }
                    }

                    itemModel.itemText.value = ""
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
                leadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedIndicatorColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            modifier = Modifier
                .focusRequester(focusRequester)
                .onFocusChanged{ state ->
                    showLabel = when{
                        state.isFocused -> {
                            false
                        }
                        else -> {
                            true
                        }
                    }
                }
                .fillMaxWidth()
                .padding(15.dp)
                .onKeyEvent { event ->
                    if (event.key == Key.Enter) {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                        itemModel.itemText.value = itemModel.itemText.value.dropLast(1)

                        // Add the current Item to Firebase on the Dispatchers.IO thread
                        val newItem = Item(
                            owner = user,
                            listId = listId,
                            text = itemModel.itemText.value,
                            done = false
                        )

                        // Update the current List on the Dispatchers.IO thread
                        scope.launch{
                            withContext(Dispatchers.IO){
                                FirebaseUtility.addItem(newItem)
                            }
                        }

                        itemModel.itemText.value = ""
                    }

                    false
                }
        )
    }
}