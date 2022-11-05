package professorchaos0802.todo.composeui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import professorchaos0802.todo.Constants
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.objects.MyList
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
    } as SnapshotStateList<MyList>

    TodoTheme(color = userViewModel.userTheme.value) {
        Scaffold() {
            ShowLists(listViewModel)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenViewPreview() {
    TodoTheme(
        color = "Blue"
    ) {
        HomeScreenView(
            UserViewModel(),
            ListViewModel()
        )
    }
}