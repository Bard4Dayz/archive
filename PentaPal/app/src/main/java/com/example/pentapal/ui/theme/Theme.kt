package com.example.pentapal.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple500,
    secondary = Red200,
    secondaryVariant = Red500,

    background = Purple700,
    surface = Purple100,
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    onSurface = Black,
)

private val LightColorPalette = lightColors(
    primary = Purple150,
    primaryVariant = Purple500,
    secondary = Red200,
    secondaryVariant = Red500,


    background = Purple0,
    surface = Purple100,
    onPrimary = White,
    onSecondary = White,
    onBackground = Black,
    onSurface = Black,

)

@Composable
fun PentaPalTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}