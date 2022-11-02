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

@Preview(showBackground = true)
@Composable
fun LightGreenTopNavPreview(){
    TodoTheme(color = "Green"){
        DefaultTopNav()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkGreenTopNavPreview(){
    TodoTheme(color = "Green", darkTheme = true){
        DefaultTopNav()
    }
}

@Preview(showBackground = true)
@Composable
fun LightRedTopNavPreview(){
    TodoTheme(color = "Red"){
        DefaultTopNav()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkRedTopNavPreview(){
    TodoTheme(color = "Red", darkTheme = true){
        DefaultTopNav()
    }
}

@Preview(showBackground = true)
@Composable
fun LightOrangeTopNavPreview(){
    TodoTheme(color = "Orange"){
        DefaultTopNav()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkOrangeTopNavPreview(){
    TodoTheme(color = "Orange", darkTheme = true){
        DefaultTopNav()
    }
}

@Preview(showBackground = true)
@Composable
fun LightPinkTopNavPreview(){
    TodoTheme(color = "Pink"){
        DefaultTopNav()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPinkTopNavPreview(){
    TodoTheme(color = "Pink", darkTheme = true){
        DefaultTopNav()
    }
}

@Preview(showBackground = true)
@Composable
fun LightPurpleTopNavPreview(){
    TodoTheme(color = "Purple"){
        DefaultTopNav()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPurpleTopNavPreview(){
    TodoTheme(color = "Purple", darkTheme = true){
        DefaultTopNav()
    }
}