package professorchaos0802.todo.composeui.splash.splashscreen

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import professorchaos0802.todo.composeui.repeatedcomponents.DefaultTopNav
import professorchaos0802.todo.composeui.splash.splashscreencontent.SplashScreenContent
import professorchaos0802.todo.theme.TodoTheme

/**
 * Splash screen view that shows while content is loading
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreenView(
    themeColor: String
){
    TodoTheme(
        color = themeColor,
    ) {
        Scaffold(
            topBar = { DefaultTopNav() }
        ){
            SplashScreenContent()
        }
    }
}
