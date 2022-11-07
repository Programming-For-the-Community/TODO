package professorchaos0802.todo.composeui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.Timestamp
import professorchaos0802.todo.Constants
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.objects.Item
import professorchaos0802.todo.objects.MyList
import professorchaos0802.todo.objects.User
import professorchaos0802.todo.theme.TodoTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenView(
    userViewModel: UserViewModel = viewModel(),
    listViewModel: ListViewModel = viewModel()
) {
    val user = userViewModel.user!!

    Log.d(Constants.HOME, "Filtering lists")
    listViewModel.lists = listViewModel.lists.filter {
        it.owner == user.username ||
                it.canEdit.contains(user.username) ||
                it.canView.contains(user.username)
    } as MutableList<MyList>

    TodoTheme(color = userViewModel.userTheme.value) {
        Scaffold() {
            ShowLists(listViewModel)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenViewPreview() {
    val user = UserViewModel()
    user.userName.value = "JDoe"
    user.user = User()

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
        list.items[0].isDone = true
    }
    TodoTheme(
        color = "Blue"
    ) {
        HomeScreenView(
            user,
            model
        )
    }
}