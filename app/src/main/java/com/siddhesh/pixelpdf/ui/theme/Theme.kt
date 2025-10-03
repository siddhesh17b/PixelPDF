package com.siddhesh.pixelpdf.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

enum class Theme {
    LIGHT, DARK, SEPIA
}

private val LightColorScheme = lightColorScheme(
    primary = md3_primary_light,
    onPrimary = md3_on_primary_light,
    primaryContainer = md3_primary_container_light,
    onPrimaryContainer = md3_on_primary_container_light,
    secondary = md3_secondary_light,
    onSecondary = md3_on_secondary_light,
    secondaryContainer = md3_secondary_container_light,
    onSecondaryContainer = md3_on_secondary_container_light,
    tertiary = md3_tertiary_light,
    onTertiary = md3_on_tertiary_light,
    tertiaryContainer = md3_tertiary_container_light,
    onTertiaryContainer = md3_on_tertiary_container_light,
    error = md3_error_light,
    onError = md3_on_error_light,
    errorContainer = md3_error_container_light,
    onErrorContainer = md3_on_error_container_light,
    surface = md3_surface_light,
    onSurface = md3_on_surface_light,
    surfaceVariant = md3_surface_variant_light,
    onSurfaceVariant = md3_on_surface_variant_light,
    outline = md3_outline_light,
    outlineVariant = md3_outline_variant_light,
)

private val DarkColorScheme = darkColorScheme(
    primary = md3_primary_dark,
    onPrimary = md3_on_primary_dark,
    primaryContainer = md3_primary_container_dark,
    onPrimaryContainer = md3_on_primary_container_dark,
    secondary = md3_secondary_dark,
    onSecondary = md3_on_secondary_dark,
    secondaryContainer = md3_secondary_container_dark,
    onSecondaryContainer = md3_on_secondary_container_dark,
    tertiary = md3_tertiary_dark,
    onTertiary = md3_on_tertiary_dark,
    tertiaryContainer = md3_tertiary_container_dark,
    onTertiaryContainer = md3_on_tertiary_container_dark,
    error = md3_error_dark,
    onError = md3_on_error_dark,
    errorContainer = md3_error_container_dark,
    onErrorContainer = md3_on_error_container_dark,
    surface = md3_surface_dark,
    onSurface = md3_on_surface_dark,
    surfaceVariant = md3_surface_variant_dark,
    onSurfaceVariant = md3_on_surface_variant_dark,
    outline = md3_outline_dark,
    outlineVariant = md3_outline_variant_dark,
)

private val SepiaColorScheme = lightColorScheme(
    primary = md3_primary_sepia,
    onPrimary = md3_on_primary_sepia,
    primaryContainer = md3_primary_container_sepia,
    onPrimaryContainer = md3_on_primary_container_sepia,
    surface = md3_surface_sepia,
    onSurface = md3_on_surface_sepia,
    surfaceVariant = md3_surface_variant_sepia,
    onSurfaceVariant = md3_on_surface_variant_sepia,
)

@Composable
fun PixelPDFTheme(
    theme: Theme = if (isSystemInDarkTheme()) Theme.DARK else Theme.LIGHT,
    content: @Composable () -> Unit
) {
    val colorScheme = when (theme) {
        Theme.LIGHT -> LightColorScheme
        Theme.DARK -> DarkColorScheme
        Theme.SEPIA -> SepiaColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = theme == Theme.LIGHT
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
