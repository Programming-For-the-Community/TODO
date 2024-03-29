package professorchaos0802.todo.composeui.list.listtopnav

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import professorchaos0802.todo.composeui.list.listcontent.listtitle.ListTitle
import professorchaos0802.todo.models.ListViewModel

/**
 * Default Top Nav Bar for [ListScreenView] unless customized for a particular view
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListTopNav(listModel: ListViewModel, readOnly: Boolean, onBackClick:() -> Unit){
    TopAppBar(
        title = { ListTitle(listModel, readOnly)
//            Text(
//            text = "TODO",
//            style = MaterialTheme.typography.titleLarge,
//            color = MaterialTheme.colorScheme.onPrimary
//        )
                },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
             IconButton(onClick = onBackClick){
                 Icon(
                     Icons.Filled.ArrowBack,
                     contentDescription = null,
                     tint = MaterialTheme.colorScheme.onPrimary,
                     modifier = Modifier.size(40.dp)
                 )
             }
        },
        actions = {},
        modifier = Modifier
    )
}