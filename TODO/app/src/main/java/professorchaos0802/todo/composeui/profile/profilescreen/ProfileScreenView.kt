package professorchaos0802.todo.composeui.profile.profilescreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import professorchaos0802.todo.composeui.profile.profiletopnav.ProfileTopNav
import professorchaos0802.todo.composeui.profileimage.profileimageicon.ProfileImageIcon
import professorchaos0802.todo.composeui.repeatedcomponents.navdrawer.NavDrawer
import professorchaos0802.todo.composeui.usercusotmization.themeselectiondropdown.ThemeSelectionDropdown
import professorchaos0802.todo.composeui.usercusotmization.usercustomizationradiobutton.UserCustomizationRadioButton
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.navigation.TodoViews
import professorchaos0802.todo.theme.TodoTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenView(
    userModel: UserViewModel = viewModel(),
    listModel: ListViewModel = viewModel(),
    onNavigateToProfile: () -> Unit,
    onNavigateToHome: () -> Unit,
    signout: () -> Unit,
    onChooseImage: () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val imageModifier = Modifier
        .size(300.dp)
        .clip(CircleShape)
        .padding(top = 75.dp)
        .clickable(
            onClick = onChooseImage
        )

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
                    ProfileTopNav(
                        userModel = userModel,
                        onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    )
                }

            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer
                        )
                ) {

                   Row(
                       verticalAlignment = Alignment.CenterVertically,
                       horizontalArrangement = Arrangement.Center,
                       modifier = Modifier
                           .fillMaxWidth()
                   ) {
                       ProfileImageIcon(userModel = userModel)
                   }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp)
                    ) {

                        Text(
                            text = userModel.userName.value,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            style = MaterialTheme.typography.titleLarge
                        )

                        Button(
                            onClick = signout,
                            modifier = Modifier.background(color = MaterialTheme.colorScheme.primary)
                        ) {
                            Text(
                                text = "Sign Out"
                            )
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp)
                    ) {

                        ThemeSelectionDropdown(userModel = userModel)
                        UserCustomizationRadioButton(text = "Public", selected = userModel.user!!.isVisible!! ) {
                        }
                        UserCustomizationRadioButton(text = "Private", selected = !userModel.user!!.isVisible!!) {
                        }
                    }
                }
            }
        }
    }
}