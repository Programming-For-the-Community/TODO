package professorchaos0802.todo.composeui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp
import professorchaos0802.todo.objects.Item
import professorchaos0802.todo.objects.MyList
import professorchaos0802.todo.theme.TodoTheme

/**
 * Displays a preview of the given [MyList] items
 *
 * @params list - [MyList]: List whose items to preview
 */
@Composable
fun ListItemPreview(list: MyList) {

    val itemsToPreview: List<Item> = if (list.items.size <= 5) {
        list.items
    } else {
        list.items.subList(0, 5)
    }

    if (itemsToPreview.isEmpty()) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .background(
                    color = MaterialTheme.colorScheme.tertiary,
                    shape = RoundedCornerShape(15)
                )
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "This list is empty",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
        }
    } else {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .background(
                    color = MaterialTheme.colorScheme.tertiary,
                    shape = RoundedCornerShape(15)
                )
        ) {
            Spacer(modifier = Modifier
                .size(15.dp)
                .fillMaxWidth()
            )

            itemsToPreview.forEach { item ->
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 15.dp)
                ) {
                    val isChecked by remember {mutableStateOf(item.isDone)}

                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = {},
                        enabled = false,
                        colors = CheckboxDefaults.colors(
                            disabledUncheckedColor = MaterialTheme.colorScheme.onTertiary,
                            disabledCheckedColor = MaterialTheme.colorScheme.onTertiary,
                            checkedColor = MaterialTheme.colorScheme.onTertiary,
                            uncheckedColor = MaterialTheme.colorScheme.onTertiary,
                            checkmarkColor = MaterialTheme.colorScheme.tertiary
                        ),
                        modifier = Modifier
                            .size(25.dp)
                            .padding(end = 15.dp)
                    )

                    Text(
                        text = item.text,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                }
            }

            Spacer(modifier = Modifier
                .size(15.dp)
                .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BlueListItemPreviewPreview() {
    val list = MyList("JDoe", "List 1")
    list.created = Timestamp.now()
    for (i in 1..10) {
        list.items.add(Item("JDoe", "Todo $i", false))
    }
    list.items[0].isDone = true

    TodoTheme(
        color = "Blue"
    ) {
        ListItemPreview(list)
    }
}

@Preview(showBackground = true)
@Composable
fun GreenListItemPreviewPreview() {
    val list = MyList("JDoe", "List 1")
    list.created = Timestamp.now()
    for (i in 1..10) {
        list.items.add(Item("JDoe", "Todo $i", false))
    }
    list.items[0].isDone = true

    TodoTheme(
        color = "Green"
    ) {
        ListItemPreview(list)
    }
}

@Preview(showBackground = true)
@Composable
fun RedListItemPreviewPreview() {
    val list = MyList("JDoe", "List 1")
    list.created = Timestamp.now()
    for (i in 1..10) {
        list.items.add(Item("JDoe", "Todo $i", false))
    }
    list.items[0].isDone = true

    TodoTheme(
        color = "Red"
    ) {
        ListItemPreview(list)
    }
}

@Preview(showBackground = true)
@Composable
fun OrangeListItemPreviewPreview() {
    val list = MyList("JDoe", "List 1")
    list.created = Timestamp.now()
    for (i in 1..10) {
        list.items.add(Item("JDoe", "Todo $i", false))
    }
    list.items[0].isDone = true

    TodoTheme(
        color = "Orange"
    ) {
        ListItemPreview(list)
    }
}

@Preview(showBackground = true)
@Composable
fun PinkListItemPreviewPreview() {
    val list = MyList("JDoe", "List 1")
    list.created = Timestamp.now()
    for (i in 1..10) {
        list.items.add(Item("JDoe", "Todo $i", false))
    }
    list.items[0].isDone = true

    TodoTheme(
        color = "Pink"
    ) {
        ListItemPreview(list)
    }
}

@Preview(showBackground = true)
@Composable
fun PurpleListItemPreviewPreview() {
    val list = MyList("JDoe", "List 1")
    list.created = Timestamp.now()
    for (i in 1..10) {
        list.items.add(Item("JDoe", "Todo $i", false))
    }
    list.items[0].isDone = true

    TodoTheme(
        color = "Purple"
    ) {
        ListItemPreview(list)
    }
}