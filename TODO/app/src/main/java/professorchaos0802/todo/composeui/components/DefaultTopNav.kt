package professorchaos0802.todo.composeui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import professorchaos0802.todo.theme.TodoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopNav(){
    TopAppBar(
        title = { Text(
            text = "TODO",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.background
        ) },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {},
        actions = {},
        modifier = Modifier
    )
}

@Preview(showBackground = true)
@Composable
fun LightBlueTopNavPreview(){
    TodoTheme(color = "Blue"){
        DefaultTopNav()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkBlueTopNavPreview(){
    TodoTheme(color = "Blue", darkTheme = true){
        DefaultTopNav()
    }
}
