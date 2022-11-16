package professorchaos0802.todo.composeui.home.listcardview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import professorchaos0802.todo.composeui.home.listitempreview.ListItemPreview
import professorchaos0802.todo.composeui.home.listtitleinfo.ListTitleInfo
import professorchaos0802.todo.objects.Item
import professorchaos0802.todo.objects.MyList

/**
 * Takes a [MyList] and displays identifying information of the list
 *
 * @param list - [MyList]: List to be previewed
 */
@Composable
fun ListCardView(list: MyList, items: List<Item>, onClick: () -> Unit) {

    Box(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(15)
            )
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            ListTitleInfo(list = list)
            ListItemPreview(items)
        }
    }
}