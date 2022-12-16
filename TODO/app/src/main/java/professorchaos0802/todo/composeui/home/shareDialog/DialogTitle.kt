package professorchaos0802.todo.composeui.home.shareDialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import professorchaos0802.todo.R

@Composable
fun DialogTitle(darkTheme: Boolean){
    val context = LocalContext.current

    Text(
        text = context.getString(R.string.dialog_title),
        color = if (darkTheme) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.secondary,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp)
    )
    Divider(
        color = MaterialTheme.colorScheme.primary,
        thickness = 0.6.dp,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(5.dp)
    )
}