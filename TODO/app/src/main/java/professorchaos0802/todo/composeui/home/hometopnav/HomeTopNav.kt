package professorchaos0802.todo.composeui.home.hometopnav

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import professorchaos0802.todo.models.UserViewModel

/**
 * Default Top Nav Bar for the app unless customized for a particular view
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopNav(userModel: UserViewModel, onClick:() -> Unit){
    TopAppBar(
        title = { Text(
            text = userModel.userName.value,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary
        ) },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            IconButton(onClick = onClick){
                if(userModel.userImage.value == ""){
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "Default User Icon",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                    )
                }else {
                    Image(
                        painter = rememberAsyncImagePainter(userModel.userImage.value),
                        contentDescription = "User Profile Image",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                    )
                }
            }             
        },
        actions = {},
        modifier = Modifier
    )
}