package professorchaos0802.todo.composeui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.firebase.Timestamp
import kotlinx.coroutines.launch
import professorchaos0802.todo.Constants
import professorchaos0802.todo.R
import professorchaos0802.todo.composeui.home.homescreenfab.HomeScreenFab
import professorchaos0802.todo.composeui.home.hometopnav.HomeTopNav
import professorchaos0802.todo.composeui.home.showlists.ShowLists
import professorchaos0802.todo.composeui.repeatedcomponents.NavDrawer
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.objects.Item
import professorchaos0802.todo.objects.MyList
import professorchaos0802.todo.objects.User
import professorchaos0802.todo.theme.TodoTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenView(
    userViewModel: UserViewModel = viewModel(),
    listViewModel: ListViewModel = viewModel(),
    navController: NavHostController,
    onNavigateToList:() -> Unit,
    onNavigateToHome:() -> Unit,
    onNavigateToProfile:() -> Unit
) {
    Log.d(Constants.HOME, "Filtering lists")
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    TodoTheme(color = userViewModel.userTheme.value) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                NavDrawer(
                    userModel = userViewModel,
                    navController = navController,
                    onNavigateToHome = {
                        scope.launch{
                            drawerState.close()
                        }
                        onNavigateToHome()
                    },
                    onNavigateToProfile = onNavigateToProfile
                )
            }
        ) {
            Scaffold(
                topBar = { HomeTopNav(userViewModel) {
                    scope.launch{
                        drawerState.open()
                    }
                } }

            ) {
                ShowLists(listViewModel, userViewModel.userName.value, onNavigateToList)

                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 25.dp, start = 15.dp, end = 15.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        HomeScreenFab(
                            icon = Icons.Filled.Share,
                            onFabClick = { /* TODO: Implement Share Logic */ }
                        )
                        HomeScreenFab(
                            icon = ImageVector.vectorResource(R.drawable.ic_baseline_edit_note_24),
                            onFabClick = {
                                listViewModel.addNewList(
                                    MyList(
                                        owner = userViewModel.userName.value,
                                        title = "Title"
                                    )
                                )
                                onNavigateToList()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenViewPreview() {
    val user = UserViewModel()
    user.userName.value = "JDoe"
    user.user = User()

    val model = ListViewModel()
    val myLists = mutableListOf<MyList>()
    myLists.add(MyList("JDoe", "List1"))
    myLists.add(MyList("JDoe", "List2"))
    myLists.add(MyList("JDoe", "List3"))
    myLists.add(MyList("JDoe", "List4"))
    myLists.forEach { list ->
        for(i in 1..5){
            list.items.add(Item("JDoe", "Todo $i", false))
        }
        list.created = Timestamp.now()
    }
    model.lists.value = myLists
    TodoTheme(
        color = "Blue"
    ) {
        HomeScreenView(
            user,
            model,
            NavHostController(LocalContext.current),
            {},
            {}
        ){}
    }
}