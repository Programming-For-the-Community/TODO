package professorchaos0802.todo.composeui.list.listcontent.listcontentscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import professorchaos0802.todo.composeui.list.listcontent.listtitle.ListTitle
import professorchaos0802.todo.models.ListViewModel

@Composable
fun ListCanEdit(model: ListViewModel, readOnly: Boolean, updateTitle:() -> Unit){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp)
    ){
       ListTitle(model, readOnly)
    }
}