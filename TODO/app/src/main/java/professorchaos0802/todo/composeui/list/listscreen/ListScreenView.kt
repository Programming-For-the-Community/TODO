package professorchaos0802.todo.composeui.list

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import professorchaos0802.todo.composeui.list.listtopnav.ListTopNav
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.theme.TodoTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreenView(
    userModel: UserViewModel = viewModel(),
    listModel: ListViewModel = viewModel(),
    onBackClick:() -> Unit
){
    TodoTheme(color = userModel.userTheme.value) {
        Scaffold(
            topBar = { ListTopNav(onBackClick) }
        ) {

        }
    }
}