package professorchaos0802.todo.composeui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import professorchaos0802.todo.Constants
import professorchaos0802.todo.R
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.navigation.TodoViews
import professorchaos0802.todo.objects.User
import professorchaos0802.todo.theme.TodoTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserNameSetupScreenView(
    userModel: UserViewModel = viewModel(),
    onNext: () -> Unit,
    onCancel: () -> Unit
) {
    val user = if (userModel.user == null) User() else userModel.user!!
    val themeColor = remember { mutableStateOf(user.theme) }

    TodoTheme(
        color = themeColor.toString()
    ) {
        Scaffold(
            topBar = { UserNameSetupTopNavBar() },
            modifier = Modifier.fillMaxSize()
        ) {
            UserNameSetupContent(userModel, onNext, onCancel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserNameSetupTopNavBar() {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserNameSetupContent(userModel: UserViewModel, onNext: () -> Unit, onCancel: () -> Unit) {
    val user = if (userModel.user == null) User() else userModel.user!!

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 25.dp, end = 25.dp)
    ) {
        UserNameTextField(user)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            UserNameSetupButton("Next") {
                userModel.updateName(user.username)
                Log.d(
                    Constants.SETUP,
                    "Navigating to Customization: ${TodoViews.Customization.route}"
                )
                onNext()
            }
            UserNameSetupButton("Cancel") {
                Log.d(Constants.SETUP, "Logging out from UserNameSetup Fragment")
                onCancel()
                Firebase.auth.signOut()
                userModel.user = null
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserNameTextField(user: User) {
    var userName by remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        value = userName,
        placeholder = {
            Text(
                text = user.username,
                style = MaterialTheme.typography.labelLarge,
                color = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
            )
        },
        label = {
            Text(
                text = "Enter your username",
                fontWeight = Bold,
                color = MaterialTheme.colorScheme.onSecondary
            )
        },
        textStyle = MaterialTheme.typography.labelLarge,
        singleLine = true,
        shape = RoundedCornerShape(25),
        onValueChange = { newName: TextFieldValue ->
            userName = newName
            user.username = newName.text
        },
        colors = textFieldColors(
            containerColor = MaterialTheme.colorScheme.primary,
            textColor = MaterialTheme.colorScheme.background,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = if (isSystemInDarkTheme()) Color.Black else Color.White
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun UserNameSetupButton(text: String, onClick: () -> Unit) {
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

@Preview(showBackground = true)
@Composable
fun UserNameSetupScreenViewPreview() {
    UserNameSetupScreenView(onNext = {}, onCancel = {})
}

@Preview(showBackground = true)
@Composable
fun UserNameSetupTopNavBarPreview() {
    TodoTheme(color = "Blue") {
        UserNameSetupTopNavBar()
    }
}