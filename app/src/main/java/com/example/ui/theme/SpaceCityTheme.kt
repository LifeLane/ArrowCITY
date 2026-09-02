package com.example.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.model.GameTheme

/**
 * Dedicated visual styling and theme constants for SPACE CITY 🚀.
 */
object SpaceCityThemeColors {
    val DeepCosmosBg = Color(0xFF0D0A1C)
    val DarkNebulaBg = Color(0xFF15102A)
    val CardSurface = Color(0xFF1F1A3A)
    val CardBorder = Color(0xFF383060)

    val CosmicViolet = Color(0xFF6C5CE7)
    val NebulaCyan = Color(0xFF00CEC9)
    val OrbitGold = Color(0xFFFDCB6E)
    val SolarCrimson = Color(0xFFFF7675)
    val ElectricBlue = Color(0xFF74B9FF)
    val EmeraldGreen = Color(0xFF00B894)

    val NodeLocked = Color(0xFF262040)
    val NodeLockedBorder = Color(0xFF3D3560)
    val NodeAvailable = Color(0xFF6C5CE7)
    val NodeCompleted = Color(0xFF00CEC9)
    val NodeActiveGlow = Color(0xFFFDCB6E)

    val PathDashedColor = Color(0xFF4A4078)
    val PathActiveGlow = Color(0xFF00CEC9)

    val SpaceGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF080612),
            Color(0xFF120E26),
            Color(0xFF1C153A),
            Color(0xFF100D22)
        )
    )

    val HeroIslandGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF2A2050),
            Color(0xFF1A1435),
            Color(0xFF0E0B20)
        )
    )

    val MasterBadgeGradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFFFF7675),
            Color(0xFFFDCB6E),
            Color(0xFF6C5CE7)
        )
    )

    /**
     * Dedicated GameTheme instance for Space City gameplay.
     */
    val SpaceCityGameTheme = GameTheme(
        id = "space_city",
        displayName = "Space City",
        background = Color(0xFF0D0A1C),
        boardBackground = Color(0xFF16122C),
        arrowStroke = Color(0xFF00CEC9),
        arrowHeadColor = Color(0xFF6C5CE7),
        hintColor = Color(0xFFFDCB6E),
        errorColor = Color(0xFFFF7675),
        dropActiveColor = Color(0xFF00CEC9),
        dropInactiveColor = Color(0xFF302850),
        headerGold = Color(0xFFFDCB6E),
        textPrimary = Color(0xFFFFFFFF),
        textSecondary = Color(0xFFB2BEC3),
        bannerBg = Color(0xFF1F1A3A),
        bannerBorder = Color(0xFF383060),
        bannerText = Color(0xFFFDCB6E),
        cardBg = Color(0xFF1F1A3A),
        buttonBg = Color(0xFF6C5CE7),
        isDark = true,
        gridLineColor = Color(0xFF262044),
        pathwayGlowColor = Color(0xFF00CEC9)
    )
}
