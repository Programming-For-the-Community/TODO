package professorchaos0802.todo.composeui.profileimage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import professorchaos0802.todo.models.UserViewModel

@Composable
fun ProfileImageIcon(userModel: UserViewModel){
    if(userModel.userImage.value == ""){
        Icon(
            Icons.Filled.Person,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .size(300.dp)
        )
    }else {
        Image(
            painter = rememberAsyncImagePainter(userModel.userImage.value),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
        )
    }
}