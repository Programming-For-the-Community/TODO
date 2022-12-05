package professorchaos0802.todo.composeui.usernamesetup.usernamesetupscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import professorchaos0802.todo.composeui.repeatedcomponents.defaulttopnav.DefaultTopNav
import professorchaos0802.todo.composeui.usercusotmization.ErrorSnackbar
import professorchaos0802.todo.composeui.usernamesetup.usernamesetupcontent.UserNameSetupContent
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.theme.TodoTheme

/**
 * Displays the content on [UserNameSetupScreenView] by calling [UserNameSetupContent]
 *
 * @param userModel - [UserViewModel]: contains user information
 * @param onNext - [Unit]: Lambda function invoked when "Next" button is pressed
 * @param onCancel - [Unit]: Lambda function invoked when "Cancel" button is pressed
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserNameSetupScreenView(
    userModel: UserViewModel = viewModel(),
    onNext: () -> Unit,
    onCancel: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    TodoTheme(
        color = userModel.userTheme.value
    ) {
        Scaffold(
            topBar = { DefaultTopNav() },
            modifier = Modifier.fillMaxSize()
        ) {
            UserNameSetupContent(
                userModel = userModel,
                snackbarHostState = snackbarHostState,
                onNext = onNext,
                onCancel = onCancel
            )

            ErrorSnackbar(hostState = snackbarHostState)
        }
    }
}
