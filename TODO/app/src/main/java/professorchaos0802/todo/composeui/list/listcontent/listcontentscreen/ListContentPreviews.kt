package professorchaos0802.todo.composeui.list.listcontent.listcontentscreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import professorchaos0802.todo.models.ItemViewModel
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.objects.MyItem
import professorchaos0802.todo.objects.MyList
import professorchaos0802.todo.theme.TodoTheme

val list = MyList(title = "Title")

@Preview(showBackground = true)
@Composable
fun BlueListContentPreview() {
    val listModel: ListViewModel = viewModel()
    listModel.currentList.value = list

    val items = mutableListOf<MyItem>()
    for (i in 1..10) {
        items.add(MyItem(owner = "JDoe", text = "Item # $i", done = false))
    }
    val itemModel: ItemViewModel = viewModel()
    itemModel.currentListItems.value = items

    TodoTheme(
        color = "Blue",
        darkTheme = false
    ) {
        ListContent(listModel, itemModel,"JDoe", true)
    }
}

@Preview(showBackground = true)
@Composable
fun GreenListContentPreview() {
    val listModel: ListViewModel = viewModel()
    listModel.currentList.value = list

    val items = mutableListOf<MyItem>()
    for (i in 1..10) {
        items.add(MyItem(owner = "JDoe", text = "Item # $i", done = false))
    }
    val itemModel: ItemViewModel = viewModel()
    itemModel.currentListItems.value = items

    TodoTheme(
        color = "Green",
        darkTheme = false
    ) {
        ListContent(listModel, itemModel,"JDoe",false)
    }
}

@Preview(showBackground = true)
@Composable
fun RedListContentPreview() {
    val listModel: ListViewModel = viewModel()
    listModel.currentList.value = list

    val items = mutableListOf<MyItem>()
    for (i in 1..10) {
        items.add(MyItem(owner = "JDoe", text = "Item # $i", done = false))
    }
    val itemModel: ItemViewModel = viewModel()
    itemModel.currentListItems.value = items

    TodoTheme(
        color = "Red",
        darkTheme = false
    ) {
        ListContent(listModel, itemModel,"JDoe", true)
    }
}

@Preview(showBackground = true)
@Composable
fun OrangeListContentPreview() {
    val listModel: ListViewModel = viewModel()
    listModel.currentList.value = list

    val items = mutableListOf<MyItem>()
    for (i in 1..10) {
        items.add(MyItem(owner = "JDoe", text = "Item # $i", done = false))
    }
    val itemModel: ItemViewModel = viewModel()
    itemModel.currentListItems.value = items

    TodoTheme(
        color = "Orange",
        darkTheme = false
    ) {
        ListContent(listModel, itemModel,"JDoe",false)
    }
}

@Preview(showBackground = true)
@Composable
fun PinkListContentPreview() {
    val listModel: ListViewModel = viewModel()
    listModel.currentList.value = list

    val items = mutableListOf<MyItem>()
    for (i in 1..10) {
        items.add(MyItem(owner = "JDoe", text = "Item # $i", done = false))
    }
    val itemModel: ItemViewModel = viewModel()
    itemModel.currentListItems.value = items

    TodoTheme(
        color = "Pink",
        darkTheme = false
    ) {
        ListContent(listModel, itemModel,"JDoe", true)
    }
}

@Preview(showBackground = true)
@Composable
fun PurpleListContentPreview() {
    val listModel: ListViewModel = viewModel()
    listModel.currentList.value = list

    val items = mutableListOf<MyItem>()
    for (i in 1..10) {
        items.add(MyItem(owner = "JDoe", text = "Item # $i", done = false))
    }
    val itemModel: ItemViewModel = viewModel()
    itemModel.currentListItems.value = items

    TodoTheme(
        color = "Purple",
        darkTheme = false
    ) {
        ListContent(listModel, itemModel,"JDoe",false)
    }
}