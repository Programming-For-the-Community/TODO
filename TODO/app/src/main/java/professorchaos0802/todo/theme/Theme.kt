package professorchaos0802.todo.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val lightBluePalette = lightColorScheme(
    background = PowderBlue,
    primary = StarCommandBlue,
    onPrimary = MiddleBlue,
    primaryContainer = PacificBlue,
    onPrimaryContainer = DarkCornflowerBlue,
    secondary = MidnightBlue,
    onSecondary = BlizzardBlue1,
    secondaryContainer = PacificBlue,
    onSecondaryContainer = DarkCornflowerBlue,
    tertiary = DarkCornflowerBlue,
    onTertiary = SkyBlueCrayola,
    tertiaryContainer = BlizzardBlue2,
    onTertiaryContainer = BlueGreen,
    surface = DarkCornflowerBlue,
    onSurface = SkyBlueCrayola
)

val darkBluePalette = darkColorScheme()

val lightGreenPalette = lightColorScheme()
val darkGreenPalette = darkColorScheme()

val lightRedPalette = lightColorScheme()
val darkRedPalette = darkColorScheme()

val lightOrangePalette = lightColorScheme()
val darkOrangePalette = darkColorScheme()

val lightPinkPalette = lightColorScheme()
val darkPinkPalette = darkColorScheme()

val lightPurplePalette = lightColorScheme()
val darkPurplePalette = darkColorScheme()

@Composable
fun TodoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    color: String,
    content: @Composable () -> Unit
){
    var colorPalette: ColorScheme

    if(darkTheme){
        colorPalette = when(color){
            "Blue" -> darkBluePalette
            "Green" -> darkGreenPalette
            "Red" -> darkRedPalette
            "Orange" -> darkOrangePalette
            "Pink" -> darkPinkPalette
            "Purple" -> darkPurplePalette
            else -> darkBluePalette
        }
    }else{
        colorPalette = when(color){
            "Blue" -> lightBluePalette
            "Green" -> lightGreenPalette
            "Red" -> lightRedPalette
            "Orange" -> lightOrangePalette
            "Pink" -> lightPinkPalette
            "Purple" -> lightPurplePalette
            else -> lightBluePalette
        }
    }

    MaterialTheme(
        colorScheme = colorPalette,
        content = content
    )
}