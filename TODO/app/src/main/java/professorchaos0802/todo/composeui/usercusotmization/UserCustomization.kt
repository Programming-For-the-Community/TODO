package professorchaos0802.todo.composeui.usercusotmization

import android.annotation.SuppressLint
import android.util.Log
import android.widget.RadioGroup
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.MenuDefaults.itemColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import professorchaos0802.todo.Constants
import professorchaos0802.todo.R
import professorchaos0802.todo.composeui.repeatedcomponents.DefaultTopNav
import professorchaos0802.todo.composeui.repeatedcomponents.ProgressionButtons
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.navigation.TodoViews
import professorchaos0802.todo.objects.User
import professorchaos0802.todo.theme.TodoTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserCustomization(
    userModel: UserViewModel = viewModel(),
    onNext: () -> Unit,
    onCancel: () -> Unit
) {

    val user = if (userModel.user == null) User() else userModel.user!!

    TodoTheme(
        color = userModel.userTheme.value
    ) {
        Scaffold(
            topBar = { DefaultTopNav() }
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 75.dp, start = 25.dp, end = 25.dp)
            ) {
                Card(
                    colors = cardColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    // Header
                    Text(
                        text = "Profile Visibility",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(start = 10.dp)
                    )

                    // Privacy Selection Radio Buttons
                    var isPublic by remember { mutableStateOf(false) }
                    var isPrivate by remember { mutableStateOf(false) }

                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 50.dp)
                    ) {
                        UserCustomizationRadioButton("Public", isPublic) {
                            isPublic = !isPublic
                            isPrivate = !isPublic
                            user.isVisible = true
                        }
                        UserCustomizationRadioButton("Private", isPrivate) {
                            isPrivate = !isPrivate
                            isPublic = !isPrivate
                            user.isVisible = false
                        }
                    }

                    ThemeSelectionDropdown(userModel) // Theme Selection Dropdown
                    ProgressionButtons(onNext, onCancel) // Progression Buttons
                }

            }
        }
    }
}

@Composable
fun UserCustomizationRadioButton(text: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                unselectedColor = MaterialTheme.colorScheme.secondary
            ),
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSelectionDropdown(userModel: UserViewModel) {
    // Theme Selection Dropdown
    var isExpanded by remember { mutableStateOf(false) }
    val themeOptions =
        listOf("Blue", "Green", "Red", "Orange", "Pink", "Purple")

    TextField(
        value = userModel.userTheme.value,
        onValueChange = {},
        enabled = false,
        label = {
            Text(
                text = "Choose a Theme Color",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondary
            )
        },
        textStyle = MaterialTheme.typography.labelLarge,
        singleLine = true,
        shape = if (isExpanded) RoundedCornerShape(25, 25, 0, 0) else RoundedCornerShape(25),
        colors = ExposedDropdownMenuDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.primary,
            disabledTextColor = MaterialTheme.colorScheme.background,
            textColor = MaterialTheme.colorScheme.background,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = if (isSystemInDarkTheme()) Color.Black else Color.White
        ),
        trailingIcon = {
            if (isExpanded) {
                Icon(
                    Icons.Filled.KeyboardArrowUp,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(top = 10.dp)
                )
            } else {
                Icon(
                    Icons.Filled.KeyboardArrowDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(top = 10.dp)
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                isExpanded = !isExpanded
            }
    )

    AnimatedVisibility(
        visible = isExpanded,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            themeOptions.forEach { color ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = color,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    onClick = {
                        isExpanded = false
                        userModel.themeEvent.value = color
                    },
                    colors = itemColors(
                        textColor = MaterialTheme.colorScheme.background,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.secondaryContainer
                        )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserCustomizationPreview() {
    UserCustomization(
        onNext = {},
        onCancel = {}
    )
}

@Preview(showBackground = true)
@Composable
fun UserCustomizationRadioButtonPreview() {
    TodoTheme(
        color = "Blue"
    ) {
        UserCustomizationRadioButton("Private", false) {}
    }
}