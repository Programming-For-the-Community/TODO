package professorchaos0802.todo.composeui.home.shareDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SelectUsers(users: List<String>, usersToAdd: MutableMap<String, String>, darkTheme: Boolean) {
    val scrollState = rememberLazyListState()
    val darkText = MaterialTheme.colorScheme.onTertiary
    val lightText = MaterialTheme.colorScheme.tertiary
    val buttonWidth = 150.dp
    val nameWidth = 200.dp

    LazyColumn(
        state = scrollState,
        contentPadding = PaddingValues(vertical = 5.dp)
    ) {
        itemsIndexed(users) { _, user ->
            val textColor = remember { mutableStateOf(if (darkTheme) darkText else lightText) }
            val rowColor = remember { mutableStateOf(Color.White) }
            val dividerColor = remember { mutableStateOf(Color.Black) }
            val visibilityOptions = listOf("Can Edit", "Can View")
            val displayedVisibility = remember{ mutableStateOf("Can Edit") }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = rowColor.value
                    )
                    .clickable {
                        if (usersToAdd.contains(user)) {
                            textColor.value = if (darkTheme) darkText else lightText
                            dividerColor.value = Color.Black
                            rowColor.value = Color.White
                            usersToAdd.remove(user)
                        } else {
                            textColor.value = Color.White
                            dividerColor.value = Color.White
                            rowColor.value = if (darkTheme) darkText else lightText
                            usersToAdd[user] = displayedVisibility.value
                        }
                    }
            ) {
                Text(
                    text = user,
                    color = textColor.value,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .width(nameWidth)
                        .padding(start = 10.dp)
                )

                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.padding(end = 5.dp)
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = textColor.value
                        ),
                        modifier = Modifier.width(buttonWidth),
                        onClick = {
                            when(displayedVisibility.value){
                                visibilityOptions[0] -> displayedVisibility.value = visibilityOptions[1]
                                else -> displayedVisibility.value = visibilityOptions[0]
                            }
                        }
                    ){
                        Text(
                            text = displayedVisibility.value,
                            color = rowColor.value,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }

            Divider(
                thickness = 0.5.dp,
                color = dividerColor.value,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}