package professorchaos0802.todo.composeui.repeatedcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.unit.dp

@Composable
fun NavDrawerItem(text: String, isCurrentView: Boolean, icon: ImageVector, onClick:() -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable{ onClick() }
            .height(32.dp)
            .fillMaxWidth()
            .background(
                color = if (isCurrentView) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.primaryContainer
            )
            .padding(start = 10.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isCurrentView) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.size(30.dp)
        )

        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = Medium,
            color = if (isCurrentView) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}
