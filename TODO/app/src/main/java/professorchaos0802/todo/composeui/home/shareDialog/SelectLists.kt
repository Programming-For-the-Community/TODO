package professorchaos0802.todo.composeui.home.shareDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import professorchaos0802.todo.objects.MyList

@Composable
fun SelectLists(lists: List<MyList>, listsToShare: MutableList<MyList>, darkTheme: Boolean) {
    val scrollState = rememberLazyListState()
    val darkText = MaterialTheme.colorScheme.onTertiary
    val lightText = MaterialTheme.colorScheme.tertiary

    LazyColumn(
        state = scrollState,
        contentPadding = PaddingValues(vertical = 5.dp)
    ) {
        itemsIndexed(lists) { _, list ->
            val textColor = remember { mutableStateOf(if (darkTheme) darkText else lightText) }
            val rowColor = remember { mutableStateOf(Color.White) }
            val dividerColor = remember { mutableStateOf(Color.Black) }

            Row(
                modifier = Modifier
                    .background(
                        color = rowColor.value
                    )
                    .clickable {
                        if (listsToShare.contains(list)) {
                            textColor.value = if (darkTheme) darkText else lightText
                            dividerColor.value = Color.Black
                            rowColor.value = Color.White
                            listsToShare.remove(list)
                        } else {
                            textColor.value = Color.White
                            dividerColor.value = Color.White
                            rowColor.value = if (darkTheme) darkText else lightText
                            listsToShare.add(list)
                        }
                    }
            ) {
                Text(
                    text = list.title,
                    color = textColor.value,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp)
                )
            }

            Divider(
                thickness = 0.5.dp,
                color = dividerColor.value,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}