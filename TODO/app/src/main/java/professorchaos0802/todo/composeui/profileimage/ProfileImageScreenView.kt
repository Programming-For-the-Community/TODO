package professorchaos0802.todo.composeui.profileimage

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import professorchaos0802.todo.Constants
import professorchaos0802.todo.composeui.usercusotmization.*
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.navigation.TodoViews
import professorchaos0802.todo.objects.User
import professorchaos0802.todo.theme.TodoTheme
import java.io.File

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileImage(
    userModel: UserViewModel = viewModel(),
    onNext: () -> Unit,
    onCancel: () -> Unit,
    onChooseImage: () -> Unit
) {
    val user = if (userModel.user == null) User() else userModel.user!!
    val themeColor = remember { mutableStateOf(user.theme) }

    TodoTheme(
        color = themeColor.toString()
    ) {
        Scaffold(
            topBar = { ProfileImageTopNavBar() }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 75.dp, start = 25.dp, end = 25.dp)
            ) {
                val context = LocalContext.current
                val imageData = remember {mutableStateOf<Uri?>(null)}
                val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()){data ->
                    imageData.value = data
                    user.img = data.toString()
                }
                val imgFile = File(imageData.value.toString())
                val bitmap = BitmapFactory.decodeFile(File(imageData.value.toString()).absolutePath)

                if(imageData.value == null){
                    Image(
                        Icons.Filled.Person,
                        contentDescription = null,
                        modifier = Modifier
                            .size(300.dp)
                    )
                }else {
                    Image(
                        painter = rememberImagePainter(data = BitmapFactory.decodeFile(File(imageData.value.toString()).absolutePath)),
                        contentDescription = null,
                        modifier = Modifier
                            .size(300.dp)
                    )
                }

                // Choose Image Button
                Button(
                    onClick = {
                        launcher.launch(
                            "image/*"
                        )
                    },
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
                ProgressionButtons(userModel, onNext, onCancel)
            }
        }
    }
}

@Composable
fun ProgressionButtons(userModel: UserViewModel, onNext: () -> Unit, onCancel: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp)
    ) {
        UserCustomizationButton(
            text = "Next"
        ) {
            userModel.user!!.hasCompletedSetup = true
            userModel.update()
            Log.d(Constants.SETUP, "Navigating to HomeFragment: ${TodoViews.Home.route}")
            onNext()
        }

        ProfileImageButton(
            text = "Cancel"
        ) {
            Log.d(Constants.SETUP, "Logging out from ProfileImageFragment")
            onCancel()
            Firebase.auth.signOut()
            userModel.user = null
        }
    }
}

@Composable
fun ProfileImageButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(25),
        modifier = Modifier
            .width(125.dp)
            .padding(top = 10.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.background
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileImageTopNavBar() {
    TopAppBar(
        title = {
            Text(
                text = "TODO",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.background
            )
        },
        navigationIcon = {},
        actions = {},
        modifier = Modifier
    )
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

@Preview(showBackground = true)
@Composable
fun ProfileImageTopNavBarPreview() {
    TodoTheme(color = "Blue") {
        ProfileImageTopNavBar()
    }
}