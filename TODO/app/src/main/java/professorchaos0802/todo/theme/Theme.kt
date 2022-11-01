package professorchaos0802.todo.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

val lightBluePalette = lightColorScheme(
    primary = LightBluePrimary,
    onPrimary = White,
    primaryContainer = LightBluePrimaryContainer,
    onPrimaryContainer = LightBlueOnPrimaryContainer,
    secondary = LightBlueSecondary,
    onSecondary = White,
    secondaryContainer = LightBlueSecondaryContainer,
    onSecondaryContainer = LightBlueOnSecondaryContainer,
    tertiary = LightBlueTertiary,
    onTertiary = White,
    tertiaryContainer = LightBlueTertiaryContainer,
    onTertiaryContainer = LightBlueOnTertiaryContainer,
    error = LightError,
    onError = White,
    errorContainer = LightErrorContainer,
    onErrorContainer = LightOnErrorContainer,
    background = White,
    onBackground = Gray10,
    surface = White,
    onSurface = Gray10,
    outline = Gray50,
    surfaceVariant = NeutralVariantGray90,
    onSurfaceVariant = Gray30
)

val darkBluePalette = darkColorScheme(
    primary = DarkBluePrimary,
    onPrimary = DarkBlueOnPrimary,
    primaryContainer = DarkBluePrimaryContainer,
    onPrimaryContainer = LightBluePrimaryContainer,
    secondary = DarkBlueSecondary,
    onSecondary = DarkBlueOnSecondary,
    secondaryContainer = DarkBlueSecondaryContainer,
    onSecondaryContainer = DarkBlueOnSecondaryContainer,
    tertiary = DarkBlueTertiary,
    onTertiary = DarkBlueTertiary,
    tertiaryContainer = DarkBlueTertiaryContainer,
    onTertiaryContainer = DarkBlueOnTertiaryContainer,
    error = DarkError,
    onError = DarkOnError,
    errorContainer = DarkErrorContainer,
    onErrorContainer = LightErrorContainer,
    background = Gray10,
    onBackground = NeutralGray90,
    surface = Gray10,
    onSurface = NeutralGray90,
    outline = Gray60,
    surfaceVariant = Gray30,
    onSurfaceVariant = Gray80
)

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
    color: String = "",
    content: @Composable () -> Unit
){
    val colorPalette: ColorScheme

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

    rememberSystemUiController().setStatusBarColor(
        color = LightBluePrimary,
        darkIcons = darkTheme
    )

    MaterialTheme(
        colorScheme = colorPalette,
        typography = TodoTypography,
        content = content
    )
}