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
    onPrimary = LightBlueOnPrimary,
    primaryContainer = LightBluePrimaryContainer,
    onPrimaryContainer = LightBlueOnPrimaryContainer,
    secondary = LightBlueSecondary,
    onSecondary = LightBlueOnSecondary,
    secondaryContainer = LightBlueSecondaryContainer,
    onSecondaryContainer = LightBlueOnSecondaryContainer,
    tertiary = LightBlueTertiary,
    onTertiary = LightBlueOnTertiary,
    tertiaryContainer = LightBlueTertiaryContainer,
    onTertiaryContainer = LightBlueOnTertiaryContainer,
    error = LightError,
    onError = White,
    errorContainer = LightErrorContainer,
    onErrorContainer = LightOnErrorContainer,
    background = LightBlueBackground,
    onBackground = LightBlueOnBackground,
    surface = LightBlueSurface,
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
    onPrimary = LightGreenOnPrimary,
    primaryContainer = LightGreenPrimaryContainer,
    onPrimaryContainer = LightGreenOnPrimaryContainer,
    secondary = LightGreenSecondary,
    onSecondary = LightGreenOnSecondary,
    secondaryContainer = LightGreenSecondaryContainer,
    onSecondaryContainer = LightGreenOnSecondaryContainer,
    tertiary = LightGreenTertiary,
    onTertiary = LightGreenOnTertiary,
    tertiaryContainer = LightGreenTertiaryContainer,
    onTertiaryContainer = LightGreenOnTertiaryContainer,
    error = LightError,
    onError = White,
    errorContainer = LightErrorContainer,
    onErrorContainer = LightOnErrorContainer,
    background = LightGreenBackground,
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
    onPrimaryContainer = DarkGreenOnPrimaryContainer,
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

val lightRedPalette = lightColorScheme(
    primary = LightRedPrimary,
    onPrimary = LightRedOnPrimary,
    primaryContainer = LightRedPrimaryContainer,
    onPrimaryContainer = LightRedOnPrimaryContainer,
    secondary = LightRedSecondary,
    onSecondary = LightRedOnSecondary,
    secondaryContainer = LightRedSecondaryContainer,
    onSecondaryContainer = LightRedOnSecondaryContainer,
    tertiary = LightRedTertiary,
    onTertiary = LightRedOnTertiary,
    tertiaryContainer = LightRedTertiaryContainer,
    onTertiaryContainer = LightRedOnTertiaryContainer,
    error = LightError,
    onError = White,
    errorContainer = LightErrorContainer,
    onErrorContainer = LightOnErrorContainer,
    background = LightRedBackground,
    onBackground = LightRedOnBackground,
    surface = LightRedSurface,
    onSurface = LightRedOnSurface,
    outline = LightRedOutline,
    surfaceVariant = LightRedSurfaceVariant,
    onSurfaceVariant = LightRedOnSurfaceVariant
)
val darkRedPalette = darkColorScheme(
    primary = DarkRedPrimary,
    onPrimary = DarkRedOnPrimary,
    primaryContainer = DarkRedPrimaryContainer,
    onPrimaryContainer = DarkRedOnPrimaryContainer,
    secondary = DarkRedSecondary,
    onSecondary = DarkRedOnSecondary,
    secondaryContainer = DarkRedSecondaryContainer,
    onSecondaryContainer = DarkRedOnSecondaryContainer,
    tertiary = DarkRedTertiary,
    onTertiary = DarkRedOnTertiary,
    tertiaryContainer = DarkRedTertiaryContainer,
    onTertiaryContainer = DarkRedOnTertiaryContainer,
    error = DarkError,
    onError = DarkOnError,
    errorContainer = DarkErrorContainer,
    onErrorContainer = LightErrorContainer,
    background = DarkRedBackground,
    onBackground = DarkRedOnBackground,
    surface = DarkRedSurface,
    onSurface = DarkRedOnSurface,
    outline = DarkRedOutline,
    surfaceVariant = DarkRedSurfaceVariant,
    onSurfaceVariant = DarkRedOnSurfaceVariant
)

val lightOrangePalette = lightColorScheme(
    primary = LightOrangePrimary,
    onPrimary = LightOrangeOnPrimary,
    primaryContainer = LightOrangePrimaryContainer,
    onPrimaryContainer = LightOrangeOnPrimaryContainer,
    secondary = LightOrangeSecondary,
    onSecondary = LightOrangeOnSecondary,
    secondaryContainer = LightOrangeSecondaryContainer,
    onSecondaryContainer = LightOrangeOnSecondaryContainer,
    tertiary = LightOrangeTertiary,
    onTertiary = LightOrangeOnTertiary,
    tertiaryContainer = LightOrangeTertiaryContainer,
    onTertiaryContainer = LightOrangeOnTertiaryContainer,
    error = LightError,
    onError = White,
    errorContainer = LightErrorContainer,
    onErrorContainer = LightOnErrorContainer,
    background = LightOrangeBackground,
    onBackground = LightOrangeOnBackground,
    surface = LightOrangeSurface,
    onSurface = LightOrangeOnSurface,
    outline = LightOrangeOutline,
    surfaceVariant = LightOrangeSurfaceVariant,
    onSurfaceVariant = LightOrangeOnSurfaceVariant
)
val darkOrangePalette = darkColorScheme(
    primary = DarkOrangePrimary,
    onPrimary = DarkOrangeOnPrimary,
    primaryContainer = DarkOrangePrimaryContainer,
    onPrimaryContainer = DarkOrangeOnPrimaryContainer,
    secondary = DarkOrangeSecondary,
    onSecondary = DarkOrangeOnSecondary,
    secondaryContainer = DarkOrangeSecondaryContainer,
    onSecondaryContainer = DarkOrangeOnSecondaryContainer,
    tertiary = DarkOrangeTertiary,
    onTertiary = DarkOrangeOnTertiary,
    tertiaryContainer = DarkOrangeTertiaryContainer,
    onTertiaryContainer = DarkOrangeOnTertiaryContainer,
    error = DarkError,
    onError = DarkOnError,
    errorContainer = DarkErrorContainer,
    onErrorContainer = LightErrorContainer,
    background = DarkOrangeBackground,
    onBackground = DarkOrangeOnBackground,
    surface = DarkOrangeSurface,
    onSurface = DarkOrangeOnSurface,
    outline = DarkOrangeOutline,
    surfaceVariant = DarkOrangeSurfaceVariant,
    onSurfaceVariant = DarkOrangeOnSurfaceVariant
)

val lightPinkPalette = lightColorScheme(
    primary = LightPinkPrimary,
    onPrimary = LightPinkOnPrimary,
    primaryContainer = LightPinkPrimaryContainer,
    onPrimaryContainer = LightPinkOnPrimaryContainer,
    secondary = LightPinkSecondary,
    onSecondary = LightPinkOnSecondary,
    secondaryContainer = LightPinkSecondaryContainer,
    onSecondaryContainer = LightPinkOnSecondaryContainer,
    tertiary = LightPinkTertiary,
    onTertiary = LightPinkOnTertiary,
    tertiaryContainer = LightPinkTertiaryContainer,
    onTertiaryContainer = LightPinkOnTertiaryContainer,
    error = LightError,
    onError = White,
    errorContainer = LightErrorContainer,
    onErrorContainer = LightOnErrorContainer,
    background = LightPinkBackground,
    onBackground = LightPinkOnBackground,
    surface = LightPinkSurface,
    onSurface = LightPinkOnSurface,
    outline = LightPinkOutline,
    surfaceVariant = LightPinkSurfaceVariant,
    onSurfaceVariant = LightPinkOnSurfaceVariant
)
val darkPinkPalette = darkColorScheme(
    primary = DarkPinkPrimary,
    onPrimary = DarkPinkOnPrimary,
    primaryContainer = DarkPinkPrimaryContainer,
    onPrimaryContainer = DarkPinkOnPrimaryContainer,
    secondary = DarkPinkSecondary,
    onSecondary = DarkPinkOnSecondary,
    secondaryContainer = DarkPinkSecondaryContainer,
    onSecondaryContainer = DarkPinkOnSecondaryContainer,
    tertiary = DarkPinkTertiary,
    onTertiary = DarkPinkOnTertiary,
    tertiaryContainer = DarkPinkTertiaryContainer,
    onTertiaryContainer = DarkPinkOnTertiaryContainer,
    error = DarkError,
    onError = DarkOnError,
    errorContainer = DarkErrorContainer,
    onErrorContainer = LightErrorContainer,
    background = DarkPinkBackground,
    onBackground = DarkPinkOnBackground,
    surface = DarkPinkSurface,
    onSurface = DarkPinkOnSurface,
    outline = DarkPinkOutline,
    surfaceVariant = DarkPinkSurfaceVariant,
    onSurfaceVariant = DarkPinkOnSurfaceVariant
)

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
            "Red" -> LightRedPrimary
            "Orange" -> LightOrangePrimary
            "Pink" -> LightPinkPrimary
            else -> LightBluePrimary
        }
    }else{
        when(color){
            "Blue" -> DarkBluePrimary
            "Green" -> DarkGreenPrimary
            "Red" -> DarkRedPrimary
            "Orange" -> DarkOrangePrimary
            "Pink" -> DarkPinkPrimary
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