package professorchaos0802.todo.composeui.usercusotmization

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import professorchaos0802.todo.composeui.repeatedcomponents.DefaultTopNav
import professorchaos0802.todo.composeui.repeatedcomponents.progressionbuttons.ProgressionButtons
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.objects.User
import professorchaos0802.todo.theme.TodoTheme

/**
 * View allowing the user to customize the theme and privacy status of their profile
 *
 * @param userModel - [UserViewModel]: viewModel containing user information
 * @param onNext - [Unit]: Lambda function executed when the "Next" button is pressed
 * @param onCancel - [Unit]: Lambda function executed when the "Cancel" button is pressed
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserCustomization(
    userModel: UserViewModel = viewModel(),
    onNext: () -> Unit,
    onCancel: () -> Unit
) {

    val user = if (userModel.user == null) User() else userModel.user!!
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    TodoTheme(
        color = userModel.userTheme.value
    ) {
        Scaffold(
            topBar = { DefaultTopNav() }
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 75.dp, start = 25.dp, end = 25.dp)
            ) {
                Card(
                    colors = cardColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    // Header
                    Text(
                        text = "Profile Visibility",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(start = 10.dp)
                    )

                    // Privacy Selection Radio Buttons
                    var isPublic by remember { mutableStateOf(false) }
                    var isPrivate by remember { mutableStateOf(false) }

                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 50.dp)
                    ) {
                        UserCustomizationRadioButton("Public", isPublic) {
                            isPublic = !isPublic
                            isPrivate = !isPublic
                            user.isVisible = true
                        }
                        UserCustomizationRadioButton("Private", isPrivate) {
                            isPrivate = !isPrivate
                            isPublic = !isPrivate
                            user.isVisible = false
                        }
                    }

                    ThemeSelectionDropdown(userModel) // Theme Selection Dropdown
                    ProgressionButtons(
                        onNext = {
                            if (user.isVisible == null) {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Choose a Profile Visibility!")
                                }
                            } else {
                                onNext()
                            }
                        },
                        onCancel = onCancel
                    ) // Progression Buttons

                    ErrorSnackbar(hostState = snackbarHostState) // Create snackbar to be launched if no profile visibility is chosen
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserCustomizationPreview() {
    UserCustomization(
        UserViewModel(),
        onNext = {},
        onCancel = {}
    )
}