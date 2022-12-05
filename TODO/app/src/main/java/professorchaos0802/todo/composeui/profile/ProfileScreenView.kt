package professorchaos0802.todo.composeui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.theme.TodoTheme

@Composable
fun ProfileScreenView(
    userModel: UserViewModel = viewModel(),
    signout:() -> Unit
){
    TodoTheme(
        color = userModel.userTheme.value
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ){
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ){
                Button(
                    onClick = signout,
                    modifier = Modifier.background(color = MaterialTheme.colorScheme.primary)
                ){
                    Text(
                        text = "Sign Out"
                    )
                }
            }
        }
    }
}