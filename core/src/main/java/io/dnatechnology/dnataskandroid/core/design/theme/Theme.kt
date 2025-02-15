package io.dnatechnology.dnataskandroid.core.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = MainBackground,
    primaryVariant = MainText,
    secondary = Black
)

private val LightColorPalette = lightColors(
    primary = MainBackground,
    primaryVariant = MainText,
    secondary = White
)

@Composable
fun DNATaskAndroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        shapes = Shapes,
        content = content
    )
}