package com.siddhesh.pixelpdf.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Display
val type_display_large = TextStyle(fontSize = 57.sp, lineHeight = 64.sp, fontWeight = FontWeight.Normal)
val type_display_medium = TextStyle(fontSize = 45.sp, lineHeight = 52.sp, fontWeight = FontWeight.Normal)
val type_display_small = TextStyle(fontSize = 36.sp, lineHeight = 44.sp, fontWeight = FontWeight.Normal)

// Headline
val type_headline_large = TextStyle(fontSize = 32.sp, lineHeight = 40.sp, fontWeight = FontWeight.Normal)
val type_headline_medium = TextStyle(fontSize = 28.sp, lineHeight = 36.sp, fontWeight = FontWeight.Normal)
val type_headline_small = TextStyle(fontSize = 24.sp, lineHeight = 32.sp, fontWeight = FontWeight.Normal)

// Title
val type_title_large = TextStyle(fontSize = 22.sp, lineHeight = 28.sp, fontWeight = FontWeight.Medium)
val type_title_medium = TextStyle(fontSize = 16.sp, lineHeight = 24.sp, fontWeight = FontWeight.Medium)
val type_title_small = TextStyle(fontSize = 14.sp, lineHeight = 20.sp, fontWeight = FontWeight.Medium)

// Body
val type_body_large = TextStyle(fontSize = 16.sp, lineHeight = 24.sp, fontWeight = FontWeight.Normal)
val type_body_medium = TextStyle(fontSize = 14.sp, lineHeight = 20.sp, fontWeight = FontWeight.Normal)
val type_body_small = TextStyle(fontSize = 12.sp, lineHeight = 16.sp, fontWeight = FontWeight.Normal)

// Label
val type_label_large = TextStyle(fontSize = 14.sp, lineHeight = 20.sp, fontWeight = FontWeight.Medium)
val type_label_medium = TextStyle(fontSize = 12.sp, lineHeight = 16.sp, fontWeight = FontWeight.Medium)
val type_label_small = TextStyle(fontSize = 11.sp, lineHeight = 16.sp, fontWeight = FontWeight.Medium)

val Typography = Typography(
    displayLarge = type_display_large,
    displayMedium = type_display_medium,
    displaySmall = type_display_small,
    headlineLarge = type_headline_large,
    headlineMedium = type_headline_medium,
    headlineSmall = type_headline_small,
    titleLarge = type_title_large,
    titleMedium = type_title_medium,
    titleSmall = type_title_small,
    bodyLarge = type_body_large,
    bodyMedium = type_body_medium,
    bodySmall = type_body_small,
    labelLarge = type_label_large,
    labelMedium = type_label_medium,
    labelSmall = type_label_small
)
