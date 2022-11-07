package professorchaos0802.todo.composeui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.objects.Item
import professorchaos0802.todo.objects.MyList
import professorchaos0802.todo.theme.TodoTheme

@Composable
fun ShowLists(listViewModel: ListViewModel){
    LazyColumn(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        userScrollEnabled = true,
        modifier = Modifier
            .fillMaxSize(1F)
            .padding(25.dp)
    ){
        items(items = listViewModel.lists){ list ->
            ListCardView(list){}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BlueShowListsPreview(){
    val model = ListViewModel()
    model.lists.add(MyList("JDoe", "List1"))
    model.lists.add(MyList("JDoe", "List2"))
    model.lists.add(MyList("JDoe", "List3"))
    model.lists.add(MyList("JDoe", "List4"))
    model.lists.forEach { list ->
        for(i in 1..5){
            list.items.add(Item("JDoe", "Todo $i", false))
        }
        list.created = Timestamp.now()
    }
    
    TodoTheme(
        color = "Blue"
    ){
        ShowLists(model)
    }
}

@Preview(showBackground = true)
@Composable
fun GreenShowListsPreview(){
    val model = ListViewModel()
    model.lists.add(MyList("JDoe", "List1"))
    model.lists.add(MyList("JDoe", "List2"))
    model.lists.add(MyList("JDoe", "List3"))
    model.lists.add(MyList("JDoe", "List4"))
    model.lists.forEach { list ->
        for(i in 1..5){
            list.items.add(Item("JDoe", "Todo $i", false))
        }
        list.created = Timestamp.now()
    }

    TodoTheme(
        color = "Green"
    ){
        ShowLists(model)
    }
}

@Preview(showBackground = true)
@Composable
fun RedShowListsPreview(){
    val model = ListViewModel()
    model.lists.add(MyList("JDoe", "List1"))
    model.lists.add(MyList("JDoe", "List2"))
    model.lists.add(MyList("JDoe", "List3"))
    model.lists.add(MyList("JDoe", "List4"))
    model.lists.forEach { list ->
        for(i in 1..5){
            list.items.add(Item("JDoe", "Todo $i", false))
        }
        list.created = Timestamp.now()
    }

    TodoTheme(
        color = "Red"
    ){
        ShowLists(model)
    }
}

@Preview(showBackground = true)
@Composable
fun OrangeShowListsPreview(){
    val model = ListViewModel()
    model.lists.add(MyList("JDoe", "List1"))
    model.lists.add(MyList("JDoe", "List2"))
    model.lists.add(MyList("JDoe", "List3"))
    model.lists.add(MyList("JDoe", "List4"))
    model.lists.forEach { list ->
        for(i in 1..5){
            list.items.add(Item("JDoe", "Todo $i", false))
        }
        list.created = Timestamp.now()
    }

    TodoTheme(
        color = "Orange"
    ){
        ShowLists(model)
    }
}

@Preview(showBackground = true)
@Composable
fun PinkShowListsPreview(){
    val model = ListViewModel()
    model.lists.add(MyList("JDoe", "List1"))
    model.lists.add(MyList("JDoe", "List2"))
    model.lists.add(MyList("JDoe", "List3"))
    model.lists.add(MyList("JDoe", "List4"))
    model.lists.forEach { list ->
        for(i in 1..5){
            list.items.add(Item("JDoe", "Todo $i", false))
        }
        list.created = Timestamp.now()
    }

    TodoTheme(
        color = "Pink"
    ){
        ShowLists(model)
    }
}

@Preview(showBackground = true)
@Composable
fun PurpleShowListsPreview(){
    val model = ListViewModel()
    model.lists.add(MyList("JDoe", "List1"))
    model.lists.add(MyList("JDoe", "List2"))
    model.lists.add(MyList("JDoe", "List3"))
    model.lists.add(MyList("JDoe", "List4"))
    model.lists.forEach { list ->
        for(i in 1..5){
            list.items.add(Item("JDoe", "Todo $i", false))
        }
        list.created = Timestamp.now()
    }

    TodoTheme(
        color = "Purple"
    ){
        ShowLists(model)
    }
}