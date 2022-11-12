package professorchaos0802.todo.composeui.home.listcardview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.Timestamp
import professorchaos0802.todo.objects.Item
import professorchaos0802.todo.objects.MyList
import professorchaos0802.todo.theme.TodoTheme

@Preview(showBackground = true)
@Composable
fun BlueListCardViewPreview() {
    val list = MyList("JDoe", "List 1")
    list.created = Timestamp.now()
    for (i in 1..5) {
        list.items.add(Item("JDoe", "Todo $i", false))
    }
    list.items[0].isDone = true

    TodoTheme(
        color = "Blue"
    ) {
        ListCardView(list, onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun GreenListCardViewPreview() {
    val list = MyList("JDoe", "List 1")
    list.created = Timestamp.now()
    for (i in 1..5) {
        list.items.add(Item("JDoe", "Todo $i", false))
    }
    list.items[0].isDone = true

    TodoTheme(
        color = "Green"
    ) {
        ListCardView(list, onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun RedListCardViewPreview() {
    val list = MyList("JDoe", "List 1")
    list.created = Timestamp.now()
    for (i in 1..5) {
        list.items.add(Item("JDoe", "Todo $i", false))
    }
    list.items[0].isDone = true

    TodoTheme(
        color = "Red"
    ) {
        ListCardView(list, onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun OrangeListCardViewPreview() {
    val list = MyList("JDoe", "List 1")
    list.created = Timestamp.now()
    for (i in 1..5) {
        list.items.add(Item("JDoe", "Todo $i", false))
    }
    list.items[0].isDone = true

    TodoTheme(
        color = "Orange"
    ) {
        ListCardView(list, onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun PinkListCardViewPreview() {
    val list = MyList("JDoe", "List 1")
    list.created = Timestamp.now()
    for (i in 1..5) {
        list.items.add(Item("JDoe", "Todo $i", false))
    }
    list.items[0].isDone = true

    TodoTheme(
        color = "Pink"
    ) {
        ListCardView(list, onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun PurpleListCardViewPreview() {
    val list = MyList("JDoe", "List 1")
    list.created = Timestamp.now()
    for (i in 1..5) {
        list.items.add(Item("JDoe", "Todo $i", false))
    }
    list.items[0].isDone = true

    TodoTheme(
        color = "Purple"
    ) {
        ListCardView(list, onClick = {})
    }
}