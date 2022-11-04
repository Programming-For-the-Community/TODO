package professorchaos0802.todo.composeui.usercusotmization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Creates a [SnackbarHost] to display error popups with a provided message
 *
 * @param hostState - [SnackBarHostState]: contains snackbar metadata
 */
@Composable
fun ErrorSnackbar(hostState: SnackbarHostState){
    SnackbarHost(
        hostState = hostState,
        modifier = Modifier
    ){ snackbarData: SnackbarData ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp, end = 50.dp)
                    .background(
                        color = MaterialTheme.colorScheme.error,
                        shape = RoundedCornerShape(25)
                    )
            ){
                Text(
                    text = snackbarData.visuals.message,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onError,
                    modifier = Modifier.padding(15.dp)
                )
            }
        }
    }
}