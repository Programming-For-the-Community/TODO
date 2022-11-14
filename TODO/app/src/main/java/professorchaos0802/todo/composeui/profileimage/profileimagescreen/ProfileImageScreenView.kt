package professorchaos0802.todo.composeui.profileimage.profileimagescreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import professorchaos0802.todo.composeui.profileimage.chooseimagebutton.ChooseImageButton
import professorchaos0802.todo.composeui.profileimage.profileimageicon.ProfileImageIcon
import professorchaos0802.todo.composeui.repeatedcomponents.defaulttopnav.DefaultTopNav
import professorchaos0802.todo.composeui.repeatedcomponents.progressionbuttons.ProgressionButtons
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.theme.TodoTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
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
                ProfileImageIcon(userModel) // User Profile Image
                ChooseImageButton(onChooseImage) // Choose Image Button
                ProgressionButtons(onNext, onCancel) // ProgressionButtons
            }
        }
    }
}