package professorchaos0802.todo.composeui.usernamesetup.usernamesetupcontent

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import professorchaos0802.todo.theme.TodoTheme

@Preview(showBackground = true)
@Composable
fun BlueUserNameSetupContentPreview(){
    TodoTheme(
        color = "Blue"
    ){
        UserNameSetupContent(viewModel(), {}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun GreenUserNameSetupContentPreview(){
    TodoTheme(
        color = "Green"
    ){
        UserNameSetupContent(viewModel(), {}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun RedUserNameSetupContentPreview(){
    TodoTheme(
        color = "Red"
    ){
        UserNameSetupContent(viewModel(), {}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun OrangeUserNameSetupContentPreview(){
    TodoTheme(
        color = "Orange"
    ){
        UserNameSetupContent(viewModel(), {}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun PinkUserNameSetupContentPreview(){
    TodoTheme(
        color = "Pink"
    ){
        UserNameSetupContent(viewModel(), {}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun PurpleUserNameSetupContentPreview(){
    TodoTheme(
        color = "Purple"
    ){
        UserNameSetupContent(viewModel(), {}, {})
    }
}