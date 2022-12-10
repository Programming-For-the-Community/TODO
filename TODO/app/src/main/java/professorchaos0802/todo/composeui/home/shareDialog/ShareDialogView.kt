package professorchaos0802.todo.composeui.home.shareDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.TextButton
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import professorchaos0802.todo.R
import professorchaos0802.todo.models.ListViewModel

@Composable
fun ShareDialog(listModel: ListViewModel, user: String, showDialog: MutableState<Boolean>){
    val context = LocalContext.current
    val darkTheme = isSystemInDarkTheme()

    Dialog(
        onDismissRequest = { showDialog.value = false}
    ){
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .background(color = Color.White)
        ){
            Text(
                text = context.getString(R.string.dialog_title),
                color = if(darkTheme) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            )
            Divider(
                thickness = 0.5.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(3.dp)
            )

            LazyColumn(
                contentPadding = PaddingValues(vertical = 5.dp)
            ){
                itemsIndexed(listModel.lists.value.filter { it.canEdit.contains(user) || it.owner == user }){_, list ->
                    Row{
                        Text(
                            text = list.title,
                            color = if(darkTheme) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.tertiary,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = Normal,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp)
                        )
                    }

                    Divider(
                        thickness = 0.5.dp,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Divider(
                thickness = 2.dp,
                color = if(darkTheme) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.secondary,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 15.dp,
                        bottom = 15.dp,
                        start = 10.dp,
                        end = 10.dp
                    )
            ){
                Button(
                    onClick = {}
                ){
                    Text("Next")
                }

                TextButton(
                    onClick = {}
                ){
                    Text("Cancel")
                }
            }
        }
    }
}