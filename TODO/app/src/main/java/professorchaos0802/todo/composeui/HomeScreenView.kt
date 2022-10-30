package professorchaos0802.todo.composeui

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.theme.TodoTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenView(
    userViewModel: UserViewModel = viewModel(),
    listViewModel: ListViewModel = viewModel()
){
    val user = userViewModel.user!!

    TodoTheme(color = user.theme) {
        Scaffold(){

        }
    }
}