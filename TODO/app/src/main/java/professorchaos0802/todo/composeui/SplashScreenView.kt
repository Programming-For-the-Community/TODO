package professorchaos0802.todo.composeui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.objects.User
import professorchaos0802.todo.theme.TodoTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreenView(
    userViewModel: UserViewModel = viewModel()
){
    val user = if(userViewModel.user == null) User() else userViewModel.user!!

    TodoTheme(
        color = user.theme,
        darkTheme = false
    ) {
        Scaffold(
            topBar = { TopNavBar() }
        ){
            SplashScreenContent()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(){
    TopAppBar(
        title = { Text(
            text = "TODO",
            style = MaterialTheme.typography.titleLarge
        ) },
        navigationIcon = {},
        actions = {},
        modifier = Modifier
    )
}

@Composable
fun SplashScreenContent(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.background
            )
    ) {
        CircularProgressIndicator()
        Text(
            text = "Loading...",
            style = MaterialTheme.typography.displayLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenViewPreview(){
    SplashScreenView()
}

@Preview(showBackground = true)
@Composable
fun TopNavBarPreview(){
    TodoTheme(color = "Blue"){
        TopNavBar()
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenContentPreview(){
    TodoTheme(color = "Blue"){
        SplashScreenContent()
    }
}