package professorchaos0802.todo.composeui.list.listscreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.objects.Item
import professorchaos0802.todo.objects.MyList
import professorchaos0802.todo.theme.TodoTheme

@Preview(showBackground = true)
@Composable
fun BlueListScreenViewPreview(){
    val userModel = UserViewModel()
    userModel.userTheme.value = "Blue"

    val listModel = ListViewModel()
    val list = MyList()
    list.title = "Title"
    list.canView.add("JDoe")
    for(i in 1..10){
        list.items.add(Item(owner = "JDoe", text = "Item # $i", isDone = false))
    }
    listModel.currentList.value = list

    TodoTheme(
        color = "Blue",
        darkTheme = true
    ) {
        ListScreenView(
            userModel = userModel,
            listModel = listModel
        ){}
    }
}

@Preview(showBackground = true)
@Composable
fun GreenListScreenViewPreview(){
    val userModel = UserViewModel()
    userModel.userTheme.value = "Green"

    val listModel = ListViewModel()
    val list = MyList()
    list.title = "Title"
    for(i in 1..10){
        list.items.add(Item(owner = "JDoe", text = "Item # $i", isDone = false))
    }
    listModel.currentList.value = list

    TodoTheme(
        color = "Green",
        darkTheme = true
    ) {
        ListScreenView(
            userModel = userModel,
            listModel = listModel
        ){}
    }
}

@Preview(showBackground = true)
@Composable
fun RedListScreenViewPreview(){
    val userModel = UserViewModel()
    userModel.userTheme.value = "Red"

    val listModel = ListViewModel()
    val list = MyList()
    list.title = "Title"
    for(i in 1..10){
        list.items.add(Item(owner = "JDoe", text = "Item # $i", isDone = false))
    }
    listModel.currentList.value = list

    TodoTheme(
        color = "Red",
        darkTheme = true
    ) {
        ListScreenView(
            userModel = userModel,
            listModel = listModel
        ){}
    }
}

@Preview(showBackground = true)
@Composable
fun OrangeListScreenViewPreview(){
    val userModel = UserViewModel()
    userModel.userTheme.value = "Orange"

    val listModel = ListViewModel()
    val list = MyList()
    list.title = "Title"
    for(i in 1..10){
        list.items.add(Item(owner = "JDoe", text = "Item # $i", isDone = false))
    }
    listModel.currentList.value = list

    TodoTheme(
        color = "Orange",
        darkTheme = true
    ) {
        ListScreenView(
            userModel = userModel,
            listModel = listModel
        ){}
    }
}

@Preview(showBackground = true)
@Composable
fun PinkListScreenViewPreview(){
    val userModel = UserViewModel()
    userModel.userTheme.value = "Pink"

    val listModel = ListViewModel()
    val list = MyList()
    list.title = "Title"
    for(i in 1..10){
        list.items.add(Item(owner = "JDoe", text = "Item # $i", isDone = false))
    }
    listModel.currentList.value = list

    TodoTheme(
        color = "Pink",
        darkTheme = true
    ) {
        ListScreenView(
            userModel = userModel,
            listModel = listModel
        ){}
    }
}

@Preview(showBackground = true)
@Composable
fun PurpleListScreenViewPreview(){
    val userModel = UserViewModel()
    userModel.userTheme.value = "Purple"

    val listModel = ListViewModel()
    val list = MyList()
    list.title = "Title"
    for(i in 1..10){
        list.items.add(Item(owner = "JDoe", text = "Item # $i", isDone = false))
    }
    listModel.currentList.value = list

    TodoTheme(
        color = "Purple",
        darkTheme = true
    ) {
        ListScreenView(
            userModel = userModel,
            listModel = listModel
        ){}
    }
}