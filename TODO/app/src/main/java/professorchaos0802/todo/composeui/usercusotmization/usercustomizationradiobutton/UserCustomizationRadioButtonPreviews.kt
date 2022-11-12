package professorchaos0802.todo.composeui.usercusotmization.usercustomizationradiobutton

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import professorchaos0802.todo.theme.TodoTheme

@Preview(showBackground = true)
@Composable
fun BlueUserCustomizationRadioButtonPreview() {
    TodoTheme(
        color = "Blue"
    ) {
        UserCustomizationRadioButton("Private", false) {}
    }
}

@Preview(showBackground = true)
@Composable
fun GreenUserCustomizationRadioButtonPreview() {
    TodoTheme(
        color = "Green"
    ) {
        UserCustomizationRadioButton("Private", false) {}
    }
}

@Preview(showBackground = true)
@Composable
fun RedUserCustomizationRadioButtonPreview() {
    TodoTheme(
        color = "Red"
    ) {
        UserCustomizationRadioButton("Private", false) {}
    }
}

@Preview(showBackground = true)
@Composable
fun OrangeUserCustomizationRadioButtonPreview() {
    TodoTheme(
        color = "Orange"
    ) {
        UserCustomizationRadioButton("Private", false) {}
    }
}

@Preview(showBackground = true)
@Composable
fun PinkUserCustomizationRadioButtonPreview() {
    TodoTheme(
        color = "Pink"
    ) {
        UserCustomizationRadioButton("Private", false) {}
    }
}

@Preview(showBackground = true)
@Composable
fun PurpleUserCustomizationRadioButtonPreview() {
    TodoTheme(
        color = "Purple"
    ) {
        UserCustomizationRadioButton("Private", false) {}
    }
}