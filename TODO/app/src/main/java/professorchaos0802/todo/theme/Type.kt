package professorchaos0802.todo.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.font.FontWeight.Companion.Thin
import androidx.compose.ui.unit.sp
import professorchaos0802.todo.R

private val Roboto = FontFamily(
    Font(R.font.roboto_black, FontWeight.Black),
    Font(R.font.roboto_black_italic, FontWeight.Black, Italic),
    Font(R.font.roboto_bold, Bold),
    Font(R.font.roboto_bold_italic, Bold, Italic),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_medium_italic, FontWeight.Medium, Italic),
    Font(R.font.roboto, Normal),
    Font(R.font.roboto_italic, Normal, Italic),
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_light_italic, FontWeight.Light, Italic),
    Font(R.font.roboto_thin, Thin),
    Font(R.font.roboto_thin_italic, Thin, Italic)
)

val TodoTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = Bold,
        fontStyle = Italic,
        fontSize = 36.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Roboto,
        fontWeight = Thin,
        fontStyle = Italic,
        fontSize = 18.sp
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
    labelSmall = TextStyle(
      fontFamily = Roboto,
      fontWeight = Bold,
      fontSize = 14.sp
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
    ),
    bodySmall = TextStyle(
      fontFamily = Roboto,
      fontWeight = Normal,
      fontStyle = Italic,
      fontSize = 10.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Roboto,
        fontWeight = Bold,
        fontSize = 24.sp
    )
)