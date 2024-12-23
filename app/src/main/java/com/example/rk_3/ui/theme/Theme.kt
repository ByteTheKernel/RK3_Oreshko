package com.example.rk_3.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Настройка цветовых схем для светлой и тёмной тем
private val LightColors = lightColorScheme(
    primary = Blue500,
    onPrimary = Color.White,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black
)

private val DarkColors = darkColorScheme(
    primary = Blue200,
    onPrimary = Color.Black,
    background = Color(0xFF121212), // Темный фон
    onBackground = Color.White,
    surface = Color(0xFF121212),
    onSurface = Color.White
)

@Composable
fun MyAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
