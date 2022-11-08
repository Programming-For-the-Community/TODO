package professorchaos0802.todo.composeui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp
import professorchaos0802.todo.objects.Item
import professorchaos0802.todo.objects.MyList
import professorchaos0802.todo.theme.TodoTheme

/**
 * Takes a [MyList] and displays identifying information of the list
 *
 * @param list - [MyList]: List to be previewed
 */
@Composable
fun ListCardView(list: MyList, onClick:() -> Unit){
    Box(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(15)
            )
    ){
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ){
            ListTitleInfo(
                list = list
            )
            ListItemPreview(list)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BlueListCardViewPreview(){
    val list = MyList("JDoe", "List 1")
    list.created = Timestamp.now()
    for(i in 1..5){
        list.items.add(Item("JDoe", "Todo $i", false))
    }
    list.items[0].isDone = true
    
    TodoTheme(
        color = "Blue"
    ){
        ListCardView(list){}
    }
}

@Preview(showBackground = true)
@Composable
fun GreenListCardViewPreview(){
    val list = MyList("JDoe", "List 1")
    list.created = Timestamp.now()
    for(i in 1..5){
        list.items.add(Item("JDoe", "Todo $i", false))
    }
    list.items[0].isDone = true

    TodoTheme(
        color = "Green"
    ){
        ListCardView(list){}
    }
}

@Preview(showBackground = true)
@Composable
fun RedListCardViewPreview(){
    val list = MyList("JDoe", "List 1")
    list.created = Timestamp.now()
    for(i in 1..5){
        list.items.add(Item("JDoe", "Todo $i", false))
    }
    list.items[0].isDone = true

    TodoTheme(
        color = "Red"
    ){
        ListCardView(list){}
    }
}

@Preview(showBackground = true)
@Composable
fun OrangeListCardViewPreview(){
    val list = MyList("JDoe", "List 1")
    list.created = Timestamp.now()
    for(i in 1..5){
        list.items.add(Item("JDoe", "Todo $i", false))
    }
    list.items[0].isDone = true

    TodoTheme(
        color = "Orange"
    ){
        ListCardView(list){}
    }
}

@Preview(showBackground = true)
@Composable
fun PinkListCardViewPreview(){
    val list = MyList("JDoe", "List 1")
    list.created = Timestamp.now()
    for(i in 1..5){
        list.items.add(Item("JDoe", "Todo $i", false))
    }
    list.items[0].isDone = true

    TodoTheme(
        color = "Pink"
    ){
        ListCardView(list){}
    }
}

@Preview(showBackground = true)
@Composable
fun PurpleListCardViewPreview(){
    val list = MyList("JDoe", "List 1")
    list.created = Timestamp.now()
    for(i in 1..5){
        list.items.add(Item("JDoe", "Todo $i", false))
    }
    list.items[0].isDone = true

    TodoTheme(
        color = "Purple"
    ){
        ListCardView(list){}
    }
}