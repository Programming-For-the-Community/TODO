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
import professorchaos0802.todo.models.ListViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ListTitle(model: ListViewModel, readOnly: Boolean){
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

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
                color = MaterialTheme.colorScheme.onSurfaceVariant
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
                model.updateCurrentList()
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
            .fillMaxWidth()
            .onKeyEvent { event ->
                if(event.key == Key.Enter){
                    focusManager.clearFocus()
                    keyboardController?.hide()
                    model.currentListTitleEvent.value = model.currentListTitle.value.dropLast(1)
                    model.updateCurrentList()
                }

                false
            }
    )
}