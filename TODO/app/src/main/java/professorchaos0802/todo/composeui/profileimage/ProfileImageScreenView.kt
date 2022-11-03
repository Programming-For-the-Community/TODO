package professorchaos0802.todo.composeui.profileimage

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import professorchaos0802.todo.composeui.repeatedcomponents.DefaultTopNav
import professorchaos0802.todo.composeui.repeatedcomponents.ProgressionButtons
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.theme.TodoTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
fun ProfileImage(
    userModel: UserViewModel = viewModel(),
    onNext: () -> Unit,
    onCancel: () -> Unit,
    onChooseImage: () -> Unit
) {
    TodoTheme(
        color = userModel.userTheme.value
    ) {
        Scaffold(
            topBar = { DefaultTopNav() }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 75.dp, start = 25.dp, end = 25.dp)
            ) {
                if(userModel.userImage.value == ""){
                    Image(
                        Icons.Filled.Person,
                        contentDescription = null,
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

                // Choose Image Button
                Button(
                    onClick = onChooseImage,
                    shape = RoundedCornerShape(25),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Text(
                        text = "Choose Image",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.background
                    )
                }

                // ProgressionButtons
                ProgressionButtons(onNext, onCancel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileImagePreview() {
    ProfileImage(
        onNext = {},
        onCancel = {},
        onChooseImage = {}
    )
}