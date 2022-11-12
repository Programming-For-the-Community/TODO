package professorchaos0802.todo.composeui.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.theme.TodoTheme

@Preview(showBackground = true)
@Composable
fun BlueListScreenViewPreview(){
    val userModel = UserViewModel()
    userModel.userTheme.value = "Blue"

    TodoTheme(
        color = "Blue",
        darkTheme = true
    ) {
        ListScreenView(
            userModel = userModel,
            listModel = ListViewModel()
        ){}
    }
}

@Preview(showBackground = true)
@Composable
fun GreenListScreenViewPreview(){
    val userModel = UserViewModel()
    userModel.userTheme.value = "Green"

    TodoTheme(
        color = "Green",
        darkTheme = true
    ) {
        ListScreenView(
            userModel = userModel,
            listModel = ListViewModel()
        ){}
    }
}

@Preview(showBackground = true)
@Composable
fun RedListScreenViewPreview(){
    val userModel = UserViewModel()
    userModel.userTheme.value = "Red"

    TodoTheme(
        color = "Red",
        darkTheme = true
    ) {
        ListScreenView(
            userModel = userModel,
            listModel = ListViewModel()
        ){}
    }
}

@Preview(showBackground = true)
@Composable
fun OrangeListScreenViewPreview(){
    val userModel = UserViewModel()
    userModel.userTheme.value = "Orange"

    TodoTheme(
        color = "Orange",
        darkTheme = true
    ) {
        ListScreenView(
            userModel = userModel,
            listModel = ListViewModel()
        ){}
    }
}

@Preview(showBackground = true)
@Composable
fun PinkListScreenViewPreview(){
    val userModel = UserViewModel()
    userModel.userTheme.value = "Pink"

    TodoTheme(
        color = "Pink",
        darkTheme = true
    ) {
        ListScreenView(
            userModel = userModel,
            listModel = ListViewModel()
        ){}
    }
}

@Preview(showBackground = true)
@Composable
fun PurpleListScreenViewPreview(){
    val userModel = UserViewModel()
    userModel.userTheme.value = "Purple"

    TodoTheme(
        color = "Purple",
        darkTheme = true
    ) {
        ListScreenView(
            userModel = userModel,
            listModel = ListViewModel()
        ){}
    }
}