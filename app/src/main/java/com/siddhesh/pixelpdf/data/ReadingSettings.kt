package com.siddhesh.pixelpdf.data

import com.siddhesh.pixelpdf.ui.theme.Theme

data class ReadingSettings(
    val theme: Theme = Theme.LIGHT,
    val fontSize: Int = 16, // in sp
    val fontWeight: Int = 400, // 100-900
    val fontWidth: Int = 100, // Variable font width percentage
    val lineSpacing: Float = 1.8f, // line height multiplier
    val margin: Int = 24, // in dp
    val fontFamily: FontFamily = FontFamily.ROBOTO_FLEX,
    val readingMode: ReadingMode = ReadingMode.PAGINATION,
    val pageAnimation: Boolean = true,
    val volumeKeyFlip: Boolean = false,
    val brightnessOverride: Float? = null, // 0.0-1.0 if overriding system
    val keepScreenOn: Boolean = true
)

enum class FontFamily {
    ROBOTO_FLEX, SYSTEM_SERIF, SYSTEM_SANS
}

enum class ReadingMode {
    PAGINATION, CONTINUOUS
}
