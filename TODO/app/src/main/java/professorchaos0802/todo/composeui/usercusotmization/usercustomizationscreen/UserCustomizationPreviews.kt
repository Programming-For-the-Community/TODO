package professorchaos0802.todo.composeui.usercusotmization.usercustomizationscreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import professorchaos0802.todo.models.UserViewModel

@Preview(showBackground = true)
@Composable
fun UserCustomizationPreview() {
    UserCustomization(
        UserViewModel(),
        onNext = {},
        onCancel = {}
    )
}