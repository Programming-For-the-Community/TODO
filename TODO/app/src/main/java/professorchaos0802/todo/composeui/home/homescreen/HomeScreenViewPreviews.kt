package professorchaos0802.todo.composeui.home.homescreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.Timestamp
import professorchaos0802.todo.models.ItemViewModel
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.objects.MyItem
import professorchaos0802.todo.objects.MyList
import professorchaos0802.todo.objects.User
import professorchaos0802.todo.theme.TodoTheme

@Preview(showBackground = true)
@Composable
fun HomeScreenViewPreview() {
    val item = ItemViewModel()
    var itemsToAdd = mutableListOf<MyItem>()
    val user = UserViewModel()
    user.userName.value = "JDoe"
    user.user = User()

    val model = ListViewModel()
    val myLists = mutableListOf<MyList>()
    myLists.add(MyList("JDoe", "List1"))
    myLists.add(MyList("JDoe", "List2"))
    myLists.add(MyList("JDoe", "List3"))
    myLists.add(MyList("JDoe", "List4"))
    myLists.forEach { list ->
        for(i in 1..5){
            itemsToAdd.add(MyItem("JDoe", list.id,"Todo $i", false))
        }
        list.created = Timestamp.now()
    }
    model.lists.value = myLists
    item.items.value = itemsToAdd
    TodoTheme(
        color = "Blue"
    ) {
        HomeScreenView(
            user,
            model,
            item,
            {},
            {}
        ){}
    }
}