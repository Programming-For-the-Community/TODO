package professorchaos0802.todo.composeui.home.listitempreview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration.Companion.LineThrough
import androidx.compose.ui.text.style.TextDecoration.Companion.None
import androidx.compose.ui.unit.dp
import professorchaos0802.todo.objects.MyItem
import professorchaos0802.todo.objects.MyList

/**
 * Displays a preview of the given [MyList] items
 *
 * @params list - [MyList]: List whose items to preview
 */
@Composable
fun ListItemPreview(items: List<MyItem>, deleteMe: Boolean) {

    val itemsToPreview: List<MyItem> = if (items.size <= 5) {
        items
    } else {
        items.subList(0, 5)
    }

    if (itemsToPreview.isEmpty()) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .background(
                    color = if(deleteMe) MaterialTheme.colorScheme.onErrorContainer else MaterialTheme.colorScheme.tertiary,
                    shape = RoundedCornerShape(15)
                )
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 10.dp, top = 15.dp, bottom = 15.dp)
            ) {
                Text(
                    text = "This list is empty",
                    style = MaterialTheme.typography.bodySmall,
                    color = if(deleteMe) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.onTertiary
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
                    color = if(deleteMe) MaterialTheme.colorScheme.onErrorContainer else MaterialTheme.colorScheme.tertiary,
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

                    Checkbox(
                        checked = item.done,
                        onCheckedChange = {},
                        enabled = false,
                        colors = CheckboxDefaults.colors(
                            disabledUncheckedColor = if(deleteMe) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.onTertiary,
                            disabledCheckedColor = if(deleteMe) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.onTertiary,
                            checkedColor = if(deleteMe) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.onTertiary,
                            uncheckedColor = if(deleteMe) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.onTertiary,
                            checkmarkColor = if(deleteMe) MaterialTheme.colorScheme.onErrorContainer else MaterialTheme.colorScheme.tertiary
                        ),
                        modifier = Modifier
                            .size(25.dp)
                            .padding(end = 15.dp)
                    )

                    Text(
                        text = item.text,
                        style = MaterialTheme.typography.bodySmall,
                        textDecoration = if(item.done) LineThrough else None,
                        color = if(deleteMe) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.onTertiary
                    )
                }
            }

            if(items.size > 5){
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 15.dp)
                ) {
                    Text(
                        text = ".......",
                        style = MaterialTheme.typography.bodyMedium,
                        color = if(deleteMe) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.onTertiary
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