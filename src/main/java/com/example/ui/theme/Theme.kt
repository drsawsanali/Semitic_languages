package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Semitic Atlas Academic Color Palettes
val RoyalGold = Color(0xFFC5A059)
val DeepGold = Color(0xFF9A7B38)
val WarmBronze = Color(0xFFB3804A)
val PapyrusBeige = Color(0xFFFBF7EE)
val AntiqueSepia = Color(0xFF704214)
val DarkSepiaBg = Color(0xFF1E1712)
val SlateStone = Color(0xFF2B3A42)
val AcademicNavy = Color(0xFF1B365D)
val LapisBlue = Color(0xFF0F4C81)
val TerracottaRed = Color(0xFFBF4A36)
val DesertSand = Color(0xFFE8DCB8)
val AncientParchment = Color(0xFFF4ECE1)

enum class ReadingThemeMode {
    DEFAULT_LIGHT,
    ANTIQUE_SEPIA,
    SEPIA_ANTIQUE,
    DARK_MODE,
    DARK,
    HIGH_CONTRAST_SLATE,
    SLATE
}

val LightAcademicColors = lightColorScheme(
    primary = DeepGold,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFF2E6C8),
    onPrimaryContainer = Color(0xFF42320D),
    secondary = AcademicNavy,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFD6E2F0),
    onSecondaryContainer = Color(0xFF0A1D33),
    tertiary = TerracottaRed,
    background = Color(0xFFFBF9F5),
    onBackground = Color(0xFF1C1B1F),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1C1B1F),
    surfaceVariant = Color(0xFFEFE7DB),
    onSurfaceVariant = Color(0xFF4B4639)
)

val SepiaAntiqueColors = lightColorScheme(
    primary = Color(0xFF8B5A2B),
    onPrimary = Color(0xFFFFF8EE),
    primaryContainer = Color(0xFFE8D3B9),
    onPrimaryContainer = Color(0xFF38220D),
    secondary = Color(0xFF5D4037),
    onSecondary = Color(0xFFFFF8EE),
    secondaryContainer = Color(0xFFD7CCC8),
    onSecondaryContainer = Color(0xFF271A14),
    tertiary = Color(0xFFA0522D),
    background = Color(0xFFF4ECE1),
    onBackground = Color(0xFF3E2723),
    surface = Color(0xFFEFE4D2),
    onSurface = Color(0xFF3E2723),
    surfaceVariant = Color(0xFFE5D5C0),
    onSurfaceVariant = Color(0xFF4E382A)
)

val DarkAcademicColors = darkColorScheme(
    primary = RoyalGold,
    onPrimary = Color(0xFF382905),
    primaryContainer = Color(0xFF594310),
    onPrimaryContainer = Color(0xFFFFDF94),
    secondary = Color(0xFF90CAF9),
    onSecondary = Color(0xFF0D2D4C),
    secondaryContainer = Color(0xFF193B60),
    onSecondaryContainer = Color(0xFFCEE5FF),
    tertiary = Color(0xFFFF8A80),
    background = Color(0xFF121214),
    onBackground = Color(0xFFE6E1E5),
    surface = Color(0xFF1E1E24),
    onSurface = Color(0xFFE6E1E5),
    surfaceVariant = Color(0xFF2C2C35),
    onSurfaceVariant = Color(0xFFCBC4CE)
)

val SlateHighContrastColors = darkColorScheme(
    primary = Color(0xFFFFD54F),
    onPrimary = Color(0xFF212121),
    primaryContainer = Color(0xFF37474F),
    onPrimaryContainer = Color(0xFFFFF59D),
    secondary = Color(0xFF80DEEA),
    onSecondary = Color(0xFF00363A),
    secondaryContainer = Color(0xFF263238),
    onSecondaryContainer = Color(0xFFB2EBF2),
    tertiary = Color(0xFFFF8A65),
    background = Color(0xFF10171D),
    onBackground = Color(0xFFECEFF1),
    surface = Color(0xFF1A232A),
    onSurface = Color(0xFFECEFF1),
    surfaceVariant = Color(0xFF263238),
    onSurfaceVariant = Color(0xFFCFD8DC)
)

val SemiticTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 36.sp
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        lineHeight = 24.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 25.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 21.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    )
)

@Composable
fun SemiticAtlasTheme(
    readingMode: ReadingThemeMode = ReadingThemeMode.DEFAULT_LIGHT,
    themeMode: ReadingThemeMode = readingMode,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val activeMode = if (themeMode != ReadingThemeMode.DEFAULT_LIGHT) themeMode else readingMode
    val colorScheme: ColorScheme = when (activeMode) {
        ReadingThemeMode.DEFAULT_LIGHT -> if (darkTheme) DarkAcademicColors else LightAcademicColors
        ReadingThemeMode.ANTIQUE_SEPIA, ReadingThemeMode.SEPIA_ANTIQUE -> SepiaAntiqueColors
        ReadingThemeMode.DARK_MODE, ReadingThemeMode.DARK -> DarkAcademicColors
        ReadingThemeMode.HIGH_CONTRAST_SLATE, ReadingThemeMode.SLATE -> SlateHighContrastColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = SemiticTypography,
        content = content
    )
}
