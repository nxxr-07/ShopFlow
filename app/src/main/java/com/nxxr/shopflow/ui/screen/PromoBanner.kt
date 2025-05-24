package com.nxxr.shopflow.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nxxr.shopflow.R

@Composable
fun PromoBanner(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(24.dp))
    ) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.banner_card),
            contentDescription = "Promo Banner",
            contentScale = ContentScale.Fit,
            alpha = 1.0f,
            modifier = Modifier.fillMaxSize()
        )

        // Overlay content: top texts with date badge
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(36.dp)
                .align(Alignment.TopStart)
        ) {
            Text(
                text = "GET 20% OFF",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Get 20% off",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .background(Color(0xFFB5FF48), shape = RoundedCornerShape(50))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "12–16 October",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                )
            }
        }

        // Bottom Row: Icon aligned bottom end (right)
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(28.dp)
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )

        // Dot indicators aligned bottom center
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 64.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                Modifier
                    .size(12.dp)
                    .background(Color(0xFFB5FF48), CircleShape)
            )
            Box(
                Modifier
                    .size(8.dp)
                    .background(Color.Gray, CircleShape)
            )
            Box(
                Modifier
                    .size(8.dp)
                    .background(Color.Gray, CircleShape)
            )
        }
    }

}


@Preview
@Composable
fun PromoBannerPreview(){
    PromoBanner()
}