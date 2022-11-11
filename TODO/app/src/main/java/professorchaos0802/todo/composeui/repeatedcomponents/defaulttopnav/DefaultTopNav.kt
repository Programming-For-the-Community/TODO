package professorchaos0802.todo.composeui.repeatedcomponents

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Default Top Nav Bar for the app unless customized for a particular view
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopNav(){
    TopAppBar(
        title = { Text(
            text = "TODO",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary
        ) },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {},
        actions = {},
        modifier = Modifier
    )
}