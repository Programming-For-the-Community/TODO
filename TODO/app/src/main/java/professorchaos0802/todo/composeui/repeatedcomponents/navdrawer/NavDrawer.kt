package professorchaos0802.todo.composeui.repeatedcomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.navigation.TodoViews

/**
 * Nav Drawer used to navigate between [HomeScreenView] and [ProfileScreenView]
 *
 * @param userModel - [UserViewModel]: contains information about the user
 */
@Composable
fun NavDrawer(
    userModel: UserViewModel = viewModel(),
    navController: NavHostController,
    onNavigateToHome:() -> Unit,
    onNavigateToProfile:() -> Unit
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .requiredWidth(200.dp)
            .fillMaxHeight()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.primary
                )
                .padding(start = 10.dp)
        ) {
            if (userModel.userImage.value == "") {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "Default User Icon",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
            } else {
                Image(
                    painter = rememberAsyncImagePainter(userModel.userImage.value),
                    contentDescription = "User Profile Image",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
            }

            Text(
                text = userModel.userName.value,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(start = 10.dp)
            )
        }

        NavDrawerItem(
            text = "HOME",
            isCurrentView = navController.currentDestination?.route == TodoViews.Home.route,
            icon = Icons.Filled.Home,
            onClick = onNavigateToHome
        )

        NavDrawerItem(
            text = "Profile",
            isCurrentView = navController.currentDestination?.route == TodoViews.Profile.route,
            icon = Icons.Filled.Person,
            onClick = onNavigateToProfile
        )
    }
}
