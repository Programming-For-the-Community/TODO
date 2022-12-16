package professorchaos0802.todo.composeui.home.shareDialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DialogButtons(selectLists: MutableState<Boolean>, darkTheme: Boolean, onCancel:() -> Unit, onNext:() -> Unit, onShare:() -> Unit){
    Divider(
        thickness = 2.dp,
        color = if (darkTheme) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.secondary,
        modifier = Modifier.fillMaxWidth()
    )

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        if(selectLists.value){
            DialogTextButton(
                text = "Next",
                onClick = onNext
            )
        }else{
            DialogTextButton(
                text = "Share",
                onClick = onShare
            )
        }

        DialogTextButton(
            text = "Cancel",
            onClick = onCancel
        )
    }
}