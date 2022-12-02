package professorchaos0802.todo.composeui.home.listcardview

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import professorchaos0802.todo.composeui.home.listitempreview.ListItemPreview
import professorchaos0802.todo.composeui.home.listtitleinfo.ListTitleInfo
import professorchaos0802.todo.objects.MyItem
import professorchaos0802.todo.objects.MyList

/**
 * Takes a [MyList] and displays identifying information of the list
 *
 * @param list - [MyList]: List to be previewed
 */
@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListCardView(list: MyList, items: List<MyItem>, username: String, listsToDelete: MutableList<MyList>, onClick: () -> Unit) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .combinedClickable(
                onClick = onClick,
                onLongClick = {
                    if(list.canView.contains(username)){
                        Toast.makeText(context, "You do not have permission to edit this list", Toast.LENGTH_SHORT).show()
                    }else{
                        list.deleteMe.value = !list.deleteMe.value
                        if(list.deleteMe.value){
                            listsToDelete.add(list)
                        }else{
                            listsToDelete.remove(list)
                        }
                    }
                }
            )
            .fillMaxWidth()
            .background(
                color = if(list.deleteMe.value) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.primaryContainer,
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
            ListItemPreview(items, list.deleteMe.value)
        }
    }
}