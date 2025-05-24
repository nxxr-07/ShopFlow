package com.nxxr.shopflow.ui.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
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
import com.nxxr.shopflow.ui.theme.background // Assuming this is your app's main dark background
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController // For Preview purposes
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun SplashScreen(
    onTimeout: () -> Unit
) {
    val logoScale = remember { Animatable(0.7f) } // Initial scale for a subtle zoom in
    val logoAlpha = remember { Animatable(0f) }

    // Launch effect for delay and animation
    LaunchedEffect(Unit) {
        // Parallel animations for a smoother entrance
        launch {
            logoScale.animateTo(1f, animationSpec = tween(durationMillis = 1500, easing = FastOutSlowInEasing))
        }
        launch {
            logoAlpha.animateTo(1f, animationSpec = tween(durationMillis = 1000, easing = LinearEasing))
        }

        delay(2500)
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.shopping_cart),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(160.dp)
                    .graphicsLayer(
                        scaleX = logoScale.value,
                        scaleY = logoScale.value,
                        alpha = logoAlpha.value
                    )
            )

            Spacer(modifier = Modifier.height(24.dp))
            ShimmerText(
                text = "Elevate Your Glow",

                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Subtle loading dot animation
            DotsLoadingIndicator()
        }
    }
}

@Composable
fun ShimmerText(text: String, style: TextStyle) {
    val shimmerColors = listOf(
        Color.White.copy(alpha = 0.2f),
        Color.White.copy(alpha = 0.5f),
        Color.White.copy(alpha = 0.2f)
    )

    val transition = rememberInfiniteTransition(label = "shimmerTransition")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "shimmerTranslate"
    )


    val brush = remember(translateAnim.value) {
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset(x = translateAnim.value - 600f, y = 0f),
            end = Offset(x = translateAnim.value, y = 0f)
        )
    }

    Text(
        text = text,
        style = style.copy(color = Color.White),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .graphicsLayer(alpha = 0.99f)
            .background(brush = brush)
    )
}

@Composable
fun DotsLoadingIndicator() {
    val dotCount = 3
    val delayUnit = 200 // Slightly faster individual dot animation
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
                            0.7f at 300
                            0f at 600
                            0f at 900
                        },
                        repeatMode = RepeatMode.Restart
                    )
                )
            }
        }
    }

    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        animatedValues.forEach { anim ->
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFB5FF48).copy(alpha = anim.value))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    // For previewing, provide a dummy onTimeout
    SplashScreen(onTimeout = { /* Do nothing for preview */ })
}