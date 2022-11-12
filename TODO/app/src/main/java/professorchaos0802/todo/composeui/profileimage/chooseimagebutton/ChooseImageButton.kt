package professorchaos0802.todo.composeui.profileimage.chooseimagebutton

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChooseImageButton(onClick:() -> Unit){
    // Choose Image Button
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(25),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Text(
            text = "Choose Image",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.background
        )
    }
}