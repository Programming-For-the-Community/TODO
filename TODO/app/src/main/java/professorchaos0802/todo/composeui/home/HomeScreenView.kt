package professorchaos0802.todo.composeui.home

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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
) {
    TodoTheme(color = userViewModel.userTheme.value) {
        Scaffold() {
            Text("You made it")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenViewPreview() {
    HomeScreenView()
}