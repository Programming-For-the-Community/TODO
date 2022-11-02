package professorchaos0802.todo.composeui.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.smallTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import professorchaos0802.todo.composeui.components.DefaultTopNav
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.objects.User
import professorchaos0802.todo.theme.TodoTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreenView(
    themeColor: String
){
    TodoTheme(
        color = themeColor,
        darkTheme = false
    ) {
        Scaffold(
            topBar = { DefaultTopNav() }
        ){
            SplashScreenContent()
        }
    }
}

@Composable
fun SplashScreenContent(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        CircularProgressIndicator()
        Text(
            text = "Loading...",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BlueSplashScreenViewPreview(){
    SplashScreenView("Blue")
}

@Preview(showBackground = true)
@Composable
fun GreenSplashScreenViewPreview(){
    SplashScreenView("Green")
}

@Preview(showBackground = true)
@Composable
fun RedSplashScreenViewPreview(){
    SplashScreenView("Red")
}

@Preview(showBackground = true)
@Composable
fun OrangeSplashScreenViewPreview(){
    SplashScreenView("Orange")
}

@Preview(showBackground = true)
@Composable
fun PinkSplashScreenViewPreview(){
    SplashScreenView("Pink")
}

@Preview(showBackground = true)
@Composable
fun BlueSplashScreenContentPreview(){
    TodoTheme(color = "Blue"){
        SplashScreenContent()
    }
}

@Preview(showBackground = true)
@Composable
fun GreenSplashScreenContentPreview(){
    TodoTheme(color = "Green"){
        SplashScreenContent()
    }
}

@Preview(showBackground = true)
@Composable
fun RedSplashScreenContentPreview(){
    TodoTheme(color = "Red"){
        SplashScreenContent()
    }
}

@Preview(showBackground = true)
@Composable
fun OrangeSplashScreenContentPreview(){
    TodoTheme(color = "Orange"){
        SplashScreenContent()
    }
}

@Preview(showBackground = true)
@Composable
fun PinkSplashScreenContentPreview(){
    TodoTheme(color = "Pink"){
        SplashScreenContent()
    }
}
