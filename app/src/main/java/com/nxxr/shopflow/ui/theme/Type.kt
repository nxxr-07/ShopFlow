package com.nxxr.shopflow.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nxxr.shopflow.R

val Tangerine = FontFamily(
    Font(R.font.tangerine, FontWeight.Normal),
    Font(R.font.tangerine, FontWeight.Medium),
    Font(R.font.tangerine, FontWeight.Bold)
)

val century_old = FontFamily(
    Font(R.font.century_old_style_std_bold, FontWeight.Normal),
    Font(R.font.century_old_style_std_bold, FontWeight.Medium),
    Font(R.font.century_old_style_std_bold, FontWeight.Bold),
)

val neuzeit_book = FontFamily(
    Font(R.font.neuzeit_sltstd_book, FontWeight.Normal),
    Font(R.font.neuzeit_sltstd_book, FontWeight.Medium),
    Font(R.font.neuzeit_sltstd_book, FontWeight.Bold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = century_old,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Tangerine,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = neuzeit_book,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)