package professorchaos0802.todo.composeui.splash.splashscreencontent

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import professorchaos0802.todo.theme.TodoTheme

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

@Preview(showBackground = true)
@Composable
fun PurpleSplashScreenContentPreview(){
    TodoTheme(color = "Purple"){
        SplashScreenContent()
    }
}