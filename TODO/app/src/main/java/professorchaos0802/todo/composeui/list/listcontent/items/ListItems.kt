package professorchaos0802.todo.composeui.list.listcontent.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import professorchaos0802.todo.models.ItemViewModel
import professorchaos0802.todo.utilities.FirebaseUtility

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ListItems(itemModel: ItemViewModel, readOnly: Boolean){
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
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
                    var currentItemText by remember{ mutableStateOf(item.text) }

                    TextField(
                        value = currentItemText,
                        readOnly = readOnly,
                        leadingIcon = {
                            Checkbox(
                                checked = item.done,
                                onCheckedChange = {
                                    itemModel.updateCurrentListItem(item)

                                    scope.launch{
                                        withContext(Dispatchers.IO){
                                            FirebaseUtility.updateItem(item, currentItemText, !item.done)
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
                        onValueChange = { newText: String ->
                            currentItemText = newText
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                                keyboardController?.hide()
                                itemModel.updateCurrentListItem(item)

                                // Add the current Item to Firebase on the Dispatchers.IO thread
                                scope.launch{
                                    withContext(Dispatchers.IO){
                                        FirebaseUtility.updateItem(item, currentItemText, item.done)
                                    }
                                }
                            }
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
                            textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            disabledTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            leadingIconColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            focusedIndicatorColor = MaterialTheme.colorScheme.tertiaryContainer,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiaryContainer
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester)
                            .onKeyEvent { event ->
                                if (event.key == Key.Enter) {
                                    focusManager.clearFocus()
                                    keyboardController?.hide()
                                    currentItemText = currentItemText.dropLast(1)
                                    itemModel.updateCurrentListItem(item)

                                    // Update the current List on the Dispatchers.IO thread
                                    scope.launch{
                                        withContext(Dispatchers.IO){
                                            FirebaseUtility.updateItem(item, currentItemText, item.done)
                                        }
                                    }
                                }

                                false
                            }
                    )
                }
            }
        }
    }

}