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
    onBackground = LightBlueOnBackground,
    surface = White,
    onSurface = LightBlueOnSurface,
    outline = LightBlueOutline,
    surfaceVariant = LightBlueSurfaceVariant,
    onSurfaceVariant = LightBlueOnSurfaceVariant
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
    onTertiary = DarkBlueOnTertiary,
    tertiaryContainer = DarkBlueTertiaryContainer,
    onTertiaryContainer = DarkBlueOnTertiaryContainer,
    error = DarkError,
    onError = DarkOnError,
    errorContainer = DarkErrorContainer,
    onErrorContainer = LightErrorContainer,
    background = DarkBlueBackground,
    onBackground = DarkBlueOnBackground,
    surface = DarkBlueSurface,
    onSurface = DarkBlueOnSurface,
    outline = DarkBlueOutline,
    surfaceVariant = DarkBlueSurfaceVariant,
    onSurfaceVariant = DarkBlueOnSurfaceVariant
)

val lightGreenPalette = lightColorScheme(
    primary = LightGreenPrimary,
    onPrimary = White,
    primaryContainer = LightGreenPrimaryContainer,
    onPrimaryContainer = LightGreenOnPrimaryContainer,
    secondary = LightGreenSecondary,
    onSecondary = White,
    secondaryContainer = LightGreenSecondaryContainer,
    onSecondaryContainer = LightGreenOnSecondaryContainer,
    tertiary = LightGreenTertiary,
    onTertiary = White,
    tertiaryContainer = LightGreenTertiaryContainer,
    onTertiaryContainer = LightGreenOnTertiaryContainer,
    error = LightError,
    onError = White,
    errorContainer = LightErrorContainer,
    onErrorContainer = LightOnErrorContainer,
    background = White,
    onBackground = LightGreenOnBackground,
    surface = LightGreenSurface,
    onSurface = LightGreenOnBackground,
    outline = LightGreenOutline,
    surfaceVariant = LightGreenSurfaceVariant,
    onSurfaceVariant = LightGreenOnSurfaceVariant
)
val darkGreenPalette = darkColorScheme(
    primary = DarkGreenPrimary,
    onPrimary = DarkGreenOnPrimary,
    primaryContainer = DarkGreenPrimaryContainer,
    onPrimaryContainer = LightGreenPrimaryContainer,
    secondary = DarkGreenSecondary,
    onSecondary = DarkGreenOnSecondary,
    secondaryContainer = DarkGreenSecondaryContainer,
    onSecondaryContainer = DarkGreenOnSecondaryContainer,
    tertiary = DarkGreenTertiary,
    onTertiary = DarkGreenOnTertiary,
    tertiaryContainer = DarkGreenTertiaryContainer,
    onTertiaryContainer = DarkGreenOnTertiaryContainer,
    error = DarkError,
    onError = DarkOnError,
    errorContainer = DarkErrorContainer,
    onErrorContainer = LightErrorContainer,
    background = DarkGreenBackground,
    onBackground = DarkGreenOnBackground,
    surface = DarkGreenSurface,
    onSurface = DarkGreenOnSurface,
    outline = DarkGreenOutline,
    surfaceVariant = DarkGreenSurfaceVariant,
    onSurfaceVariant = DarkGreenOnSurfaceVariant
)

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

    val statusBarColor = if(darkTheme){
        when(color){
            "Blue" -> LightBluePrimary
            "Green" -> LightGreenPrimary
            else -> LightBluePrimary
        }
    }else{
        when(color){
            "Blue" -> DarkBluePrimary
            "Green" -> DarkGreenPrimary
            else -> DarkBluePrimary
        }
    }

    rememberSystemUiController().setStatusBarColor(
        color = statusBarColor,
        darkIcons = !darkTheme
    )

    MaterialTheme(
        colorScheme = colorPalette,
        typography = TodoTypography,
        content = content
    )
}