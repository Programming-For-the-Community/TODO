package professorchaos0802.todo.composeui.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import professorchaos0802.todo.composeui.home.hometopnav.HomeTopNav
import professorchaos0802.todo.composeui.repeatedcomponents.navdrawer.NavDrawer
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.navigation.TodoViews
import professorchaos0802.todo.objects.MyList
import professorchaos0802.todo.theme.TodoTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenView(
    userModel: UserViewModel = viewModel(),
    listModel: ListViewModel = viewModel(),
    onNavigateToProfile: () -> Unit,
    onNavigateToHome: () -> Unit,
    signout: () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val listsToDelete = remember { mutableStateListOf<MyList>() }
    val showShareDialog = remember { mutableStateOf(false) }

    TodoTheme(color = userModel.userTheme.value) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                NavDrawer(
                    userModel = userModel,
                    currentRoute = TodoViews.Profile.route,
                    onNavigateToHome = {
                        scope.launch {
                            drawerState.close()
                        }
                        onNavigateToHome()
                    },
                    onNavigateToProfile = onNavigateToProfile
                )
            }
        ) {
            Scaffold(
                topBar = {
                    HomeTopNav(
                        userModel = userModel,
                        listsToDelete = listsToDelete,
                        onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        },
                        onDelete = {

                        }
                    )
                }

            ){
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ){
                    Button(
                        onClick = signout,
                        modifier = Modifier.background(color = MaterialTheme.colorScheme.primary)
                    ) {
                        Text(
                            text = "Sign Out"
                        )
                    }
                }
            }
        }
    }
}