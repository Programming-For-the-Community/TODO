package professorchaos0802.todo.composeui.repeatedcomponents

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import professorchaos0802.todo.theme.TodoTheme

/**
 * Button style used for the user setup progression, two of these are displayed in [ProgressionButtons]
 * 
 * @param text - [String]: text to be shown on the button
 * @param onClick - [Unit]: Lambda called when button is pressed
 */
@Composable
fun ProgressionButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(25),
        modifier = Modifier
            .width(125.dp)
            .padding(top = 10.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.background
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LightBlueProgressionButtonPreview(){
    TodoTheme(
        color = "Blue",
        darkTheme = false
    ){
        ProgressionButton("Text", {})
    }
}

@Preview(showBackground = true)
@Composable
fun LightGreenProgressionButtonPreview(){
    TodoTheme(
        color = "Green",
        darkTheme = false
    ){
        ProgressionButton("Text", {})
    }
}

@Preview(showBackground = true)
@Composable
fun LightRedProgressionButtonPreview(){
    TodoTheme(
        color = "Red",
        darkTheme = false
    ){
        ProgressionButton("Text", {})
    }
}

@Preview(showBackground = true)
@Composable
fun LightOrangeProgressionButtonPreview(){
    TodoTheme(
        color = "Orange",
        darkTheme = false
    ){
        ProgressionButton("Text", {})
    }
}

@Preview(showBackground = true)
@Composable
fun LightPinkProgressionButtonPreview(){
    TodoTheme(
        color = "Pink",
        darkTheme = false
    ){
        ProgressionButton("Text", {})
    }
}

@Preview(showBackground = true)
@Composable
fun LightPurpleProgressionButtonPreview(){
    TodoTheme(
        color = "Purple",
        darkTheme = false
    ){
        ProgressionButton("Text", {})
    }
}
