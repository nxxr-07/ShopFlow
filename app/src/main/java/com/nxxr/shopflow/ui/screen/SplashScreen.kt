package com.nxxr.shopflow.ui.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nxxr.shopflow.R
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import com.nxxr.shopflow.ui.theme.background
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    onTimeout: () -> Unit
) {
    val logoAlpha = remember { Animatable(0f) }

    // Launch effect for delay and animation
    LaunchedEffect(Unit) {
        logoAlpha.animateTo(1f, animationSpec = tween(durationMillis = 1000))
        delay(2000)
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010)), // deep black
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Brand Logo or Icon (SVG/vector preferred)
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(120.dp)
                    .alpha(logoAlpha.value)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Brand tagline
            ShimmerText(text = "Elevate Your Glow", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(32.dp))

            // Subtle loading dot animation
            DotsLoadingIndicator()
        }
    }
}

@Composable
fun ShimmerText(text: String, style: TextStyle) {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.White,
        Color.LightGray.copy(alpha = 0.6f)
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing)
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    Text(
        text = text,
        style = style,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .graphicsLayer(alpha = 0.99f)
            .background(brush = brush)
    )
}


@Composable
fun DotsLoadingIndicator() {
    val dotCount = 3
    val delayUnit = 300
    val animatedValues = List(dotCount) { remember { Animatable(0f) } }

    LaunchedEffect(Unit) {
        animatedValues.forEachIndexed { index, animatable ->
            launch {
                delay(index * delayUnit.toLong())
                animatable.animateTo(
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = keyframes {
                            durationMillis = 900
                            0f at 0
                            1f at 300
                            0f at 600
                        }
                    )
                )
            }
        }
    }

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        animatedValues.forEach { anim ->
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFB9F227).copy(alpha = anim.value))
            )
        }
    }
}
