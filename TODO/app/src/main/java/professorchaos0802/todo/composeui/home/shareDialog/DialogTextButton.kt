package professorchaos0802.todo.composeui.home.shareDialog

import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DialogTextButton(text: String, onClick:() -> Unit){
    TextButton(
        onClick = onClick
    ){
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}