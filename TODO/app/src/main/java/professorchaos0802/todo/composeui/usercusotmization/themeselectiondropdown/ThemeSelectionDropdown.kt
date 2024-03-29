package professorchaos0802.todo.composeui.usercusotmization.themeselectiondropdown

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import professorchaos0802.todo.Constants
import professorchaos0802.todo.models.UserViewModel

/**
 * Dropdown menu for the user to indicate their choice of color theme during [UserCustomization]
 * setup
 *
 * @param userModel - [UserViewModel]: viewModel containing all user information
 */
@SuppressLint("CommitPrefEdits")
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
                color = MaterialTheme.colorScheme.primaryContainer
            )
        },
        textStyle = MaterialTheme.typography.labelLarge,
        singleLine = true,
        shape = if (isExpanded) RoundedCornerShape(25, 25, 0, 0) else RoundedCornerShape(25),
        colors = ExposedDropdownMenuDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.primary,
            disabledTextColor = MaterialTheme.colorScheme.onPrimary,
            textColor = MaterialTheme.colorScheme.onPrimary,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = if (isSystemInDarkTheme()) Color.Black else Color.White
        ),
        trailingIcon = {
            Icon(
                if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .size(50.dp)
                    .padding(top = 10.dp)
            )
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
        val context = LocalContext.current
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
                        val sharedPreferences = context.getSharedPreferences(Constants.THEME_KEY, Context.MODE_PRIVATE)
                        sharedPreferences.edit().putString(Constants.THEME_KEY, userModel.userTheme.value).apply()
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer
                        )
                )
            }
        }
    }
}