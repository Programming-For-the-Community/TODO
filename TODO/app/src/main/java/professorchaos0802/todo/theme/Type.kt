package professorchaos0802.todo.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.unit.sp
import professorchaos0802.todo.R

private val Roboto = FontFamily(
    Font(R.font.roboto_black, FontWeight.Black),
    Font(R.font.roboto_black_italic, FontWeight.Black, FontStyle.Italic),
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_medium_italic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.roboto, FontWeight.Normal),
    Font(R.font.roboto_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.roboto_thin, FontWeight.Thin),
    Font(R.font.roboto_thin_italic, FontWeight.Thin, FontStyle.Italic)
)

val TodoTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = Bold,
        fontStyle = Italic,
        fontSize = 36.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = Bold,
        fontSize = 24.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = Bold,
        fontStyle = Italic,
        fontSize = 24.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Roboto,
        fontWeight = Bold,
        fontSize = 18.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = Bold,
        fontSize = 24.sp

    ),
    bodyMedium = TextStyle(
        fontFamily = Roboto,
        fontWeight = Normal,
        fontSize = 20.sp
    )
)