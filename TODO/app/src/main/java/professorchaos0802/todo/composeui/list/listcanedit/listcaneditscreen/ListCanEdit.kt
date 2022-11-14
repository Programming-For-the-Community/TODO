package professorchaos0802.todo.composeui.list.listcanedit.listcaneditscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import professorchaos0802.todo.models.ListViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ListCanEdit(model: ListViewModel, readOnly: Boolean, updateTitle:() -> Unit){
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp)
    ){
        TextField(
            value = model.currentListTitle.value,
            readOnly = readOnly,
            textStyle = MaterialTheme.typography.labelLarge,
            leadingIcon = {
//              Row(
//                  verticalAlignment = Alignment.Bottom,
//                  horizontalArrangement = Arrangement.Center,
//                  modifier = Modifier
//                      .fillMaxHeight()
////                      .padding(bottom = 5.dp)
//              ){
                  Icon(
                      Icons.Filled.List,
                      contentDescription = null,
                      modifier = Modifier.size(40.dp)
                  )
//              }
            },
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
                .height(64.dp)
                .fillMaxWidth()
        )
    }
}