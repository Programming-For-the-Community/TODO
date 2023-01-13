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
fun SelectUsers(users: List<String>, usersToAdd: MutableList<String>, darkTheme: Boolean) {
    val scrollState = rememberLazyListState()
    val darkText = MaterialTheme.colorScheme.onTertiary
    val lightText = MaterialTheme.colorScheme.tertiary

    LazyColumn(
        state = scrollState,
        contentPadding = PaddingValues(vertical = 5.dp)
    ) {
        itemsIndexed(users) { _, user ->
            val textColor = remember { mutableStateOf(if (darkTheme) darkText else lightText) }
            val rowColor = remember { mutableStateOf(Color.White) }
            val dividerColor = remember { mutableStateOf(Color.Black) }
            var isSwitched = remember { mutableStateOf(false) }

            Row(
                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween,
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
                            usersToAdd.add(user)
                        }
                    }
            ) {
                Text(
                    text = user,
                    color = textColor.value,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = if(isSwitched.value) "Can Edit" else "Can View",
                        color = textColor.value,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Normal,
                    )

                    Switch(
                        checked = isSwitched.value,
                        onCheckedChange = { isSwitched.value = !isSwitched.value },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = textColor.value,
                            checkedTrackColor = if(darkTheme) lightText else darkText,
                            uncheckedThumbColor = textColor.value,
                            uncheckedTrackColor = if(darkTheme) lightText else darkText
                        ),
                        modifier = Modifier
                            .padding(1.dp)
                            .size(1.dp)
                    )
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