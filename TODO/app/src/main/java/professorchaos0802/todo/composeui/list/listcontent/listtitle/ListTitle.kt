package professorchaos0802.todo.composeui.list.listcontent.listtitle

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.utilities.FirebaseUtility

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ListTitle(model: ListViewModel, readOnly: Boolean){
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    TextField(
        value = model.currentListTitle.value,
        readOnly = readOnly,
        textStyle = MaterialTheme.typography.labelLarge,
//        leadingIcon = {
//            Icon(
//                Icons.Filled.List,
//                contentDescription = null,
//                modifier = Modifier.size(40.dp)
//            )
//        },
        placeholder = {
            Text(
                text = model.currentListTitle.value,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        label = {},
        singleLine = true,
        onValueChange = { newTitle ->
            model.currentListTitleEvent.value = newTitle
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                keyboardController?.hide()

                // Update the current List on the Dispatchers.IO thread
                scope.launch{
                    withContext(Dispatchers.IO){
                        FirebaseUtility.updateCurrentList(
                            currentList = model.currentList.value!!,
                            currentTitle = model.currentListTitle.value
                        )
                    }
                }
            }
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colorScheme.primary,
            textColor = MaterialTheme.colorScheme.onPrimary,
            cursorColor = MaterialTheme.colorScheme.onPrimary,
            leadingIconColor = MaterialTheme.colorScheme.onPrimary,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .focusRequester(focusRequester)
            .fillMaxWidth()
            .onKeyEvent { event ->
                if (event.key == Key.Enter) {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                    model.currentListTitleEvent.value = model.currentListTitle.value.dropLast(1)

                    // Update the current List on the Dispatchers.IO thread
                    scope.launch{
                        withContext(Dispatchers.IO){
                            FirebaseUtility.updateCurrentList(
                                currentList = model.currentList.value!!,
                                currentTitle = model.currentListTitle.value
                            )
                        }
                    }
                }

                false
            }
    )
}