package com.example.ui.theme

import androidx.compose.ui.graphics.Color
import com.example.model.GameTheme

object GameThemes {
    // 1. Cyber Metropolis (Cyberpunk Neon Grid)
    val CyberNeon = GameTheme(
        id = "cyber_neon",
        displayName = "Cyber Metropolis",
        background = Color(0xFF070B14),
        boardBackground = Color(0xFF0E1626),
        arrowStroke = Color(0xFF00E5FF),
        arrowHeadColor = Color(0xFFFBBF24),
        hintColor = Color(0xFFF43F5E),
        errorColor = Color(0xFFEF4444),
        dropActiveColor = Color(0xFF00F0FF),
        dropInactiveColor = Color(0xFF1E293B),
        headerGold = Color(0xFFFBBF24),
        textPrimary = Color(0xFFF8FAFC),
        textSecondary = Color(0xFF94A3B8),
        bannerBg = Color(0xFF131D31),
        bannerBorder = Color(0xFF00E5FF).copy(alpha = 0.4f),
        bannerText = Color(0xFF00E5FF),
        cardBg = Color(0xFF0E1626),
        buttonBg = Color(0xFF1A263C),
        isDark = true,
        gridLineColor = Color(0xFF00E5FF).copy(alpha = 0.22f),
        pathwayGlowColor = Color(0xFF00E5FF).copy(alpha = 0.6f),
        themeModeType = "cyber_metropolis"
    )

    // 2. Midnight Stardust (Deep Space & Nebulae)
    val MidnightStardust = GameTheme(
        id = "midnight_stardust",
        displayName = "Midnight Stardust",
        background = Color(0xFF090A1A),
        boardBackground = Color(0xFF12142E),
        arrowStroke = Color(0xFFA5B4FC),
        arrowHeadColor = Color(0xFFE0E7FF),
        hintColor = Color(0xFF38BDF8),
        errorColor = Color(0xFFF43F5E),
        dropActiveColor = Color(0xFF818CF8),
        dropInactiveColor = Color(0xFF312E81),
        headerGold = Color(0xFFFDE047),
        textPrimary = Color(0xFFF1F5F9),
        textSecondary = Color(0xFFA5B4FC),
        bannerBg = Color(0xFF1E1B4B),
        bannerBorder = Color(0xFF6366F1).copy(alpha = 0.4f),
        bannerText = Color(0xFFC7D2FE),
        cardBg = Color(0xFF131638),
        buttonBg = Color(0xFF232859),
        isDark = true,
        gridLineColor = Color(0xFF818CF8).copy(alpha = 0.20f),
        pathwayGlowColor = Color(0xFFC084FC).copy(alpha = 0.55f),
        themeModeType = "midnight_stardust"
    )

    // 3. Warm Birch & Linen (Zen Nordic Wood & Eye Comfort)
    val EyeComfort = GameTheme(
        id = "eye_comfort",
        displayName = "Warm Birch & Linen",
        background = Color(0xFFFBF8F1),
        boardBackground = Color(0xFFF4EDE0),
        arrowStroke = Color(0xFF5D4037),
        arrowHeadColor = Color(0xFFD97706),
        hintColor = Color(0xFF0288D1),
        errorColor = Color(0xFFDC2626),
        dropActiveColor = Color(0xFF3B82F6),
        dropInactiveColor = Color(0xFFDCCFBD),
        headerGold = Color(0xFFB45309),
        textPrimary = Color(0xFF3E2723),
        textSecondary = Color(0xFF795548),
        bannerBg = Color(0xFFEADBCE),
        bannerBorder = Color(0xFFC4B294),
        bannerText = Color(0xFF5D4037),
        cardBg = Color(0xFFFFFDF8),
        buttonBg = Color(0xFFFFFFFF),
        isDark = false,
        gridLineColor = Color(0xFFBCAAA4).copy(alpha = 0.35f),
        pathwayGlowColor = Color(0xFFF59E0B).copy(alpha = 0.45f),
        themeModeType = "birch_linen"
    )

    // 4. Kyoto Matcha (Zen Garden Sanctuary)
    val MatchaMoss = GameTheme(
        id = "matcha_moss",
        displayName = "Kyoto Matcha",
        background = Color(0xFFF3F7F2),
        boardBackground = Color(0xFFE5EEE4),
        arrowStroke = Color(0xFF2D5A27),
        arrowHeadColor = Color(0xFF4ADE80),
        hintColor = Color(0xFF0284C7),
        errorColor = Color(0xFFDC2626),
        dropActiveColor = Color(0xFF22C55E),
        dropInactiveColor = Color(0xFFC7D7C5),
        headerGold = Color(0xFF15803D),
        textPrimary = Color(0xFF14532D),
        textSecondary = Color(0xFF4B7253),
        bannerBg = Color(0xFFD6E6D4),
        bannerBorder = Color(0xFFA3CFA0),
        bannerText = Color(0xFF166534),
        cardBg = Color(0xFFFAFCFA),
        buttonBg = Color(0xFFFFFFFF),
        isDark = false,
        gridLineColor = Color(0xFF4ADE80).copy(alpha = 0.28f),
        pathwayGlowColor = Color(0xFF10B981).copy(alpha = 0.5f),
        themeModeType = "kyoto_matcha"
    )

    // 5. Solar Sunset & Dune (Golden Hour Sahara)
    val SolarSunset = GameTheme(
        id = "solar_sunset",
        displayName = "Solar Sunset & Dune",
        background = Color(0xFFFFF7ED),
        boardBackground = Color(0xFFFFEDD5),
        arrowStroke = Color(0xFF9A3412),
        arrowHeadColor = Color(0xFFF97316),
        hintColor = Color(0xFFD97706),
        errorColor = Color(0xFFDC2626),
        dropActiveColor = Color(0xFFF97316),
        dropInactiveColor = Color(0xFFFED7AA),
        headerGold = Color(0xFFEA580C),
        textPrimary = Color(0xFF431407),
        textSecondary = Color(0xFF9A3412),
        bannerBg = Color(0xFFFFE0BA),
        bannerBorder = Color(0xFFFDBA74),
        bannerText = Color(0xFF9A3412),
        cardBg = Color(0xFFFFFFFF),
        buttonBg = Color(0xFFFFFFFF),
        isDark = false,
        gridLineColor = Color(0xFFF97316).copy(alpha = 0.25f),
        pathwayGlowColor = Color(0xFFFB923C).copy(alpha = 0.5f),
        themeModeType = "solar_sunset"
    )

    // 6. Mariana Ocean Trench (Abyssal Bioluminescence)
    val OceanBreeze = GameTheme(
        id = "ocean_breeze",
        displayName = "Mariana Ocean Trench",
        background = Color(0xFF041527),
        boardBackground = Color(0xFF09233F),
        arrowStroke = Color(0xFF2DD4BF),
        arrowHeadColor = Color(0xFF38BDF8),
        hintColor = Color(0xFF38BDF8),
        errorColor = Color(0xFFF43F5E),
        dropActiveColor = Color(0xFF06B6D4),
        dropInactiveColor = Color(0xFF1E3A5F),
        headerGold = Color(0xFF2DD4BF),
        textPrimary = Color(0xFFF0FDFA),
        textSecondary = Color(0xFF67E8F9),
        bannerBg = Color(0xFF0F3255),
        bannerBorder = Color(0xFF2DD4BF).copy(alpha = 0.35f),
        bannerText = Color(0xFF5EEAD4),
        cardBg = Color(0xFF0A2542),
        buttonBg = Color(0xFF143B66),
        isDark = true,
        gridLineColor = Color(0xFF2DD4BF).copy(alpha = 0.24f),
        pathwayGlowColor = Color(0xFF06B6D4).copy(alpha = 0.6f),
        themeModeType = "mariana_ocean"
    )

    // 7. Sakura Spring Blossom (Petals & Pastel Calm)
    val CherryBlossom = GameTheme(
        id = "cherry_blossom",
        displayName = "Sakura Spring Blossom",
        background = Color(0xFFFFF1F2),
        boardBackground = Color(0xFFFFE4E6),
        arrowStroke = Color(0xFF881337),
        arrowHeadColor = Color(0xFFF43F5E),
        hintColor = Color(0xFF9333EA),
        errorColor = Color(0xFFE11D48),
        dropActiveColor = Color(0xFFFB7185),
        dropInactiveColor = Color(0xFFFECDD3),
        headerGold = Color(0xFFBE123C),
        textPrimary = Color(0xFF4C0519),
        textSecondary = Color(0xFF9F1239),
        bannerBg = Color(0xFFFED7E2),
        bannerBorder = Color(0xFFFDA4AF),
        bannerText = Color(0xFF9F1239),
        cardBg = Color(0xFFFFF8FA),
        buttonBg = Color(0xFFFFFFFF),
        isDark = false,
        gridLineColor = Color(0xFFF43F5E).copy(alpha = 0.22f),
        pathwayGlowColor = Color(0xFFFDA4AF).copy(alpha = 0.55f),
        themeModeType = "sakura_blossom"
    )

    // 8. Monolith Dark Matter (OLED High Contrast Minimalist)
    val MonolithDark = GameTheme(
        id = "monolith_dark",
        displayName = "Monolith Dark Matter",
        background = Color(0xFF000000),
        boardBackground = Color(0xFF0C0C0C),
        arrowStroke = Color(0xFFE2E8F0),
        arrowHeadColor = Color(0xFFFFFFFF),
        hintColor = Color(0xFF38BDF8),
        errorColor = Color(0xFFEF4444),
        dropActiveColor = Color(0xFFF8FAFC),
        dropInactiveColor = Color(0xFF27272A),
        headerGold = Color(0xFFE2E8F0),
        textPrimary = Color(0xFFFFFFFF),
        textSecondary = Color(0xFFA1A1AA),
        bannerBg = Color(0xFF18181B),
        bannerBorder = Color(0xFF3F3F46),
        bannerText = Color(0xFFFAFAFA),
        cardBg = Color(0xFF0F0F10),
        buttonBg = Color(0xFF27272A),
        isDark = true,
        gridLineColor = Color(0xFFFFFFFF).copy(alpha = 0.18f),
        pathwayGlowColor = Color(0xFFFFFFFF).copy(alpha = 0.6f),
        themeModeType = "monolith_dark"
    )

    // 9. Obsidian Inferno (Molten Core & Magma)
    val ObsidianInferno = GameTheme(
        id = "obsidian_inferno",
        displayName = "Obsidian Inferno",
        background = Color(0xFF110705),
        boardBackground = Color(0xFF1F0D09),
        arrowStroke = Color(0xFFEA580C),
        arrowHeadColor = Color(0xFFEF4444),
        hintColor = Color(0xFFF59E0B),
        errorColor = Color(0xFFDC2626),
        dropActiveColor = Color(0xFFF97316),
        dropInactiveColor = Color(0xFF431407),
        headerGold = Color(0xFFF59E0B),
        textPrimary = Color(0xFFFFF7ED),
        textSecondary = Color(0xFFFDBA74),
        bannerBg = Color(0xFF2D140D),
        bannerBorder = Color(0xFFEA580C).copy(alpha = 0.4f),
        bannerText = Color(0xFFFB923C),
        cardBg = Color(0xFF1F0D09),
        buttonBg = Color(0xFF361810),
        isDark = true,
        gridLineColor = Color(0xFFEF4444).copy(alpha = 0.28f),
        pathwayGlowColor = Color(0xFFF97316).copy(alpha = 0.65f),
        themeModeType = "obsidian_inferno"
    )

    // 10. Royal Amethyst (Imperial Velvet & Gold Filigree)
    val RoyalAmethyst = GameTheme(
        id = "royal_amethyst",
        displayName = "Royal Amethyst",
        background = Color(0xFF140722),
        boardBackground = Color(0xFF220C38),
        arrowStroke = Color(0xFFC084FC),
        arrowHeadColor = Color(0xFFFBBF24),
        hintColor = Color(0xFF38BDF8),
        errorColor = Color(0xFFF43F5E),
        dropActiveColor = Color(0xFFA855F7),
        dropInactiveColor = Color(0xFF4C1D95),
        headerGold = Color(0xFFFBBF24),
        textPrimary = Color(0xFFFAF5FF),
        textSecondary = Color(0xFFD8B4FE),
        bannerBg = Color(0xFF30124E),
        bannerBorder = Color(0xFFFBBF24).copy(alpha = 0.35f),
        bannerText = Color(0xFFFDE047),
        cardBg = Color(0xFF240E3C),
        buttonBg = Color(0xFF3C1763),
        isDark = true,
        gridLineColor = Color(0xFFC084FC).copy(alpha = 0.22f),
        pathwayGlowColor = Color(0xFFFBBF24).copy(alpha = 0.6f),
        themeModeType = "royal_amethyst"
    )

    // Legacy alias
    val ZenNight = MidnightStardust

    val allThemes = listOf(
        CyberNeon,
        MidnightStardust,
        EyeComfort,
        MatchaMoss,
        SolarSunset,
        OceanBreeze,
        CherryBlossom,
        MonolithDark,
        ObsidianInferno,
        RoyalAmethyst
    )
}

