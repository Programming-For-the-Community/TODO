package professorchaos0802.todo.composeui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.Timestamp
import professorchaos0802.todo.Constants
import professorchaos0802.todo.R
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
    listViewModel: ListViewModel = viewModel(),
    onFabClick:() -> Unit
) {
    val user = userViewModel.user!!

    Log.d(Constants.HOME, "Filtering lists")


    TodoTheme(color = userViewModel.userTheme.value) {
        Scaffold(
            topBar = { HomeTopNav(userViewModel){/* TODO: Navigate to Profile/Settings View */} }
        ) {
            ShowLists(listViewModel, userViewModel.userName.value)

            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 25.dp, start = 15.dp, end = 15.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    HomeScreenFab(
                        icon = Icons.Filled.Share,
                        onFabClick = { /* TODO: Implement Share Logic */ }
                    )
                    HomeScreenFab(
                        icon = ImageVector.vectorResource(R.drawable.ic_baseline_edit_note_24),
                        onFabClick = onFabClick
                    )
                }
            }
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
    val myLists = mutableListOf<MyList>()
    myLists.add(MyList("JDoe", "List1"))
    myLists.add(MyList("JDoe", "List2"))
    myLists.add(MyList("JDoe", "List3"))
    myLists.add(MyList("JDoe", "List4"))
    myLists.forEach { list ->
        for(i in 1..5){
            list.items.add(Item("JDoe", "Todo $i", false))
        }
        list.created = Timestamp.now()
    }
    model.lists.value = myLists
    TodoTheme(
        color = "Blue"
    ) {
        HomeScreenView(
            user,
            model
        ){}
    }
}