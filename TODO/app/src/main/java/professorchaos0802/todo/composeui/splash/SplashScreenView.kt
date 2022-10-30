package professorchaos0802.todo.composeui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    val themeColor = remember { mutableStateOf(user.theme) }

    TodoTheme(
        color = themeColor.toString(),
        darkTheme = false
    ) {
        Scaffold(
            topBar = { SplashTopNavBar() }
        ){
            SplashScreenContent()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashTopNavBar(){
    TopAppBar(
        title = { Text(
            text = "TODO",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.background
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
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = "Loading...",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.secondary
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
fun SplashTopNavBarPreview(){
    TodoTheme(color = "Blue"){
        SplashTopNavBar()
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenContentPreview(){
    TodoTheme(color = "Blue"){
        SplashScreenContent()
    }
}