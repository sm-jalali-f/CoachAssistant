package com.freez.coachassistant.ui.theme
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext

private val lightScheme = lightColorScheme(
    primary = _root_ide_package_.com.freez.coachassistant.ui.theme.primaryLight,
    onPrimary = _root_ide_package_.com.freez.coachassistant.ui.theme.onPrimaryLight,
    primaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.primaryContainerLight,
    onPrimaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.onPrimaryContainerLight,
    secondary = _root_ide_package_.com.freez.coachassistant.ui.theme.secondaryLight,
    onSecondary = _root_ide_package_.com.freez.coachassistant.ui.theme.onSecondaryLight,
    secondaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.secondaryContainerLight,
    onSecondaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.onSecondaryContainerLight,
    tertiary = _root_ide_package_.com.freez.coachassistant.ui.theme.tertiaryLight,
    onTertiary = _root_ide_package_.com.freez.coachassistant.ui.theme.onTertiaryLight,
    tertiaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.tertiaryContainerLight,
    onTertiaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.onTertiaryContainerLight,
    error = _root_ide_package_.com.freez.coachassistant.ui.theme.errorLight,
    onError = _root_ide_package_.com.freez.coachassistant.ui.theme.onErrorLight,
    errorContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.errorContainerLight,
    onErrorContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.onErrorContainerLight,
    background = _root_ide_package_.com.freez.coachassistant.ui.theme.backgroundLight,
    onBackground = _root_ide_package_.com.freez.coachassistant.ui.theme.onBackgroundLight,
    surface = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceLight,
    onSurface = _root_ide_package_.com.freez.coachassistant.ui.theme.onSurfaceLight,
    surfaceVariant = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceVariantLight,
    onSurfaceVariant = _root_ide_package_.com.freez.coachassistant.ui.theme.onSurfaceVariantLight,
    outline = _root_ide_package_.com.freez.coachassistant.ui.theme.outlineLight,
    outlineVariant = _root_ide_package_.com.freez.coachassistant.ui.theme.outlineVariantLight,
    scrim = _root_ide_package_.com.freez.coachassistant.ui.theme.scrimLight,
    inverseSurface = _root_ide_package_.com.freez.coachassistant.ui.theme.inverseSurfaceLight,
    inverseOnSurface = _root_ide_package_.com.freez.coachassistant.ui.theme.inverseOnSurfaceLight,
    inversePrimary = _root_ide_package_.com.freez.coachassistant.ui.theme.inversePrimaryLight,
    surfaceDim = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceDimLight,
    surfaceBright = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceBrightLight,
    surfaceContainerLowest = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceContainerLowestLight,
    surfaceContainerLow = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceContainerLowLight,
    surfaceContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceContainerLight,
    surfaceContainerHigh = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceContainerHighLight,
    surfaceContainerHighest = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = _root_ide_package_.com.freez.coachassistant.ui.theme.primaryDark,
    onPrimary = _root_ide_package_.com.freez.coachassistant.ui.theme.onPrimaryDark,
    primaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.primaryContainerDark,
    onPrimaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.onPrimaryContainerDark,
    secondary = _root_ide_package_.com.freez.coachassistant.ui.theme.secondaryDark,
    onSecondary = _root_ide_package_.com.freez.coachassistant.ui.theme.onSecondaryDark,
    secondaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.secondaryContainerDark,
    onSecondaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.onSecondaryContainerDark,
    tertiary = _root_ide_package_.com.freez.coachassistant.ui.theme.tertiaryDark,
    onTertiary = _root_ide_package_.com.freez.coachassistant.ui.theme.onTertiaryDark,
    tertiaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.tertiaryContainerDark,
    onTertiaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.onTertiaryContainerDark,
    error = _root_ide_package_.com.freez.coachassistant.ui.theme.errorDark,
    onError = _root_ide_package_.com.freez.coachassistant.ui.theme.onErrorDark,
    errorContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.errorContainerDark,
    onErrorContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.onErrorContainerDark,
    background = _root_ide_package_.com.freez.coachassistant.ui.theme.backgroundDark,
    onBackground = _root_ide_package_.com.freez.coachassistant.ui.theme.onBackgroundDark,
    surface = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceDark,
    onSurface = _root_ide_package_.com.freez.coachassistant.ui.theme.onSurfaceDark,
    surfaceVariant = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceVariantDark,
    onSurfaceVariant = _root_ide_package_.com.freez.coachassistant.ui.theme.onSurfaceVariantDark,
    outline = _root_ide_package_.com.freez.coachassistant.ui.theme.outlineDark,
    outlineVariant = _root_ide_package_.com.freez.coachassistant.ui.theme.outlineVariantDark,
    scrim = _root_ide_package_.com.freez.coachassistant.ui.theme.scrimDark,
    inverseSurface = _root_ide_package_.com.freez.coachassistant.ui.theme.inverseSurfaceDark,
    inverseOnSurface = _root_ide_package_.com.freez.coachassistant.ui.theme.inverseOnSurfaceDark,
    inversePrimary = _root_ide_package_.com.freez.coachassistant.ui.theme.inversePrimaryDark,
    surfaceDim = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceDimDark,
    surfaceBright = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceBrightDark,
    surfaceContainerLowest = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceContainerLowestDark,
    surfaceContainerLow = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceContainerLowDark,
    surfaceContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceContainerDark,
    surfaceContainerHigh = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceContainerHighDark,
    surfaceContainerHighest = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = _root_ide_package_.com.freez.coachassistant.ui.theme.primaryLightMediumContrast,
    onPrimary = _root_ide_package_.com.freez.coachassistant.ui.theme.onPrimaryLightMediumContrast,
    primaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.primaryContainerLightMediumContrast,
    onPrimaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.onPrimaryContainerLightMediumContrast,
    secondary = _root_ide_package_.com.freez.coachassistant.ui.theme.secondaryLightMediumContrast,
    onSecondary = _root_ide_package_.com.freez.coachassistant.ui.theme.onSecondaryLightMediumContrast,
    secondaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.secondaryContainerLightMediumContrast,
    onSecondaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.onSecondaryContainerLightMediumContrast,
    tertiary = _root_ide_package_.com.freez.coachassistant.ui.theme.tertiaryLightMediumContrast,
    onTertiary = _root_ide_package_.com.freez.coachassistant.ui.theme.onTertiaryLightMediumContrast,
    tertiaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.tertiaryContainerLightMediumContrast,
    onTertiaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.onTertiaryContainerLightMediumContrast,
    error = _root_ide_package_.com.freez.coachassistant.ui.theme.errorLightMediumContrast,
    onError = _root_ide_package_.com.freez.coachassistant.ui.theme.onErrorLightMediumContrast,
    errorContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.errorContainerLightMediumContrast,
    onErrorContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.onErrorContainerLightMediumContrast,
    background = _root_ide_package_.com.freez.coachassistant.ui.theme.backgroundLightMediumContrast,
    onBackground = _root_ide_package_.com.freez.coachassistant.ui.theme.onBackgroundLightMediumContrast,
    surface = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceLightMediumContrast,
    onSurface = _root_ide_package_.com.freez.coachassistant.ui.theme.onSurfaceLightMediumContrast,
    surfaceVariant = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceVariantLightMediumContrast,
    onSurfaceVariant = _root_ide_package_.com.freez.coachassistant.ui.theme.onSurfaceVariantLightMediumContrast,
    outline = _root_ide_package_.com.freez.coachassistant.ui.theme.outlineLightMediumContrast,
    outlineVariant = _root_ide_package_.com.freez.coachassistant.ui.theme.outlineVariantLightMediumContrast,
    scrim = _root_ide_package_.com.freez.coachassistant.ui.theme.scrimLightMediumContrast,
    inverseSurface = _root_ide_package_.com.freez.coachassistant.ui.theme.inverseSurfaceLightMediumContrast,
    inverseOnSurface = _root_ide_package_.com.freez.coachassistant.ui.theme.inverseOnSurfaceLightMediumContrast,
    inversePrimary = _root_ide_package_.com.freez.coachassistant.ui.theme.inversePrimaryLightMediumContrast,
    surfaceDim = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceDimLightMediumContrast,
    surfaceBright = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceBrightLightMediumContrast,
    surfaceContainerLowest = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceContainerLowLightMediumContrast,
    surfaceContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceContainerLightMediumContrast,
    surfaceContainerHigh = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = _root_ide_package_.com.freez.coachassistant.ui.theme.primaryLightHighContrast,
    onPrimary = _root_ide_package_.com.freez.coachassistant.ui.theme.onPrimaryLightHighContrast,
    primaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.primaryContainerLightHighContrast,
    onPrimaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.onPrimaryContainerLightHighContrast,
    secondary = _root_ide_package_.com.freez.coachassistant.ui.theme.secondaryLightHighContrast,
    onSecondary = _root_ide_package_.com.freez.coachassistant.ui.theme.onSecondaryLightHighContrast,
    secondaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.secondaryContainerLightHighContrast,
    onSecondaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.onSecondaryContainerLightHighContrast,
    tertiary = _root_ide_package_.com.freez.coachassistant.ui.theme.tertiaryLightHighContrast,
    onTertiary = _root_ide_package_.com.freez.coachassistant.ui.theme.onTertiaryLightHighContrast,
    tertiaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.tertiaryContainerLightHighContrast,
    onTertiaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.onTertiaryContainerLightHighContrast,
    error = _root_ide_package_.com.freez.coachassistant.ui.theme.errorLightHighContrast,
    onError = _root_ide_package_.com.freez.coachassistant.ui.theme.onErrorLightHighContrast,
    errorContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.errorContainerLightHighContrast,
    onErrorContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.onErrorContainerLightHighContrast,
    background = _root_ide_package_.com.freez.coachassistant.ui.theme.backgroundLightHighContrast,
    onBackground = _root_ide_package_.com.freez.coachassistant.ui.theme.onBackgroundLightHighContrast,
    surface = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceLightHighContrast,
    onSurface = _root_ide_package_.com.freez.coachassistant.ui.theme.onSurfaceLightHighContrast,
    surfaceVariant = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceVariantLightHighContrast,
    onSurfaceVariant = _root_ide_package_.com.freez.coachassistant.ui.theme.onSurfaceVariantLightHighContrast,
    outline = _root_ide_package_.com.freez.coachassistant.ui.theme.outlineLightHighContrast,
    outlineVariant = _root_ide_package_.com.freez.coachassistant.ui.theme.outlineVariantLightHighContrast,
    scrim = _root_ide_package_.com.freez.coachassistant.ui.theme.scrimLightHighContrast,
    inverseSurface = _root_ide_package_.com.freez.coachassistant.ui.theme.inverseSurfaceLightHighContrast,
    inverseOnSurface = _root_ide_package_.com.freez.coachassistant.ui.theme.inverseOnSurfaceLightHighContrast,
    inversePrimary = _root_ide_package_.com.freez.coachassistant.ui.theme.inversePrimaryLightHighContrast,
    surfaceDim = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceDimLightHighContrast,
    surfaceBright = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceBrightLightHighContrast,
    surfaceContainerLowest = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceContainerLowLightHighContrast,
    surfaceContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceContainerLightHighContrast,
    surfaceContainerHigh = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = _root_ide_package_.com.freez.coachassistant.ui.theme.surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = _root_ide_package_.com.freez.coachassistant.ui.theme.primaryDarkMediumContrast,
    onPrimary = _root_ide_package_.com.freez.coachassistant.ui.theme.onPrimaryDarkMediumContrast,
    primaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.primaryContainerDarkMediumContrast,
    onPrimaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.onPrimaryContainerDarkMediumContrast,
    secondary = _root_ide_package_.com.freez.coachassistant.ui.theme.secondaryDarkMediumContrast,
    onSecondary = _root_ide_package_.com.freez.coachassistant.ui.theme.onSecondaryDarkMediumContrast,
    secondaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.secondaryContainerDarkMediumContrast,
    onSecondaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.onSecondaryContainerDarkMediumContrast,
    tertiary = _root_ide_package_.com.freez.coachassistant.ui.theme.tertiaryDarkMediumContrast,
    onTertiary = _root_ide_package_.com.freez.coachassistant.ui.theme.onTertiaryDarkMediumContrast,
    tertiaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = _root_ide_package_.com.freez.coachassistant.ui.theme.onTertiaryContainerDarkMediumContrast,
    error = _root_ide_package_.com.freez.coachassistant.ui.theme.errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun CoachAssistantTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
//      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//          val context = LocalContext.current
//          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//      }
      
      darkTheme -> darkScheme
      else -> lightScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = content
  )
}

