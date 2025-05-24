package com.nxxr.shopflow.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nxxr.shopflow.R
import com.nxxr.shopflow.data.ProductModel
import com.nxxr.shopflow.ui.theme.onBackground
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nxxr.shopflow.Screen
import com.nxxr.shopflow.ui.ShopTopBar
import com.nxxr.shopflow.ui.theme.background
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class) // Annotate with ExperimentalMaterial3Api for ModalBottomSheet
@Composable
fun ShopScreen(
    modifier: Modifier = Modifier,
    viewModel: ShopViewModel = ShopViewModel(),
    navHostController: NavHostController
) {
    val products by viewModel.products.collectAsState()
    val category by viewModel.categories.collectAsState()

    // State for the bottom sheet visibility
    var showBottomSheet by remember { mutableStateOf(false) }
    // State to hold the selected product
    var selectedProduct by remember { mutableStateOf<ProductModel?>(null) }
    // Bottom sheet state for controlling its behavior (expand/collapse)
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier.fillMaxSize() // Make column fill max size to allow sheet on top
    ) {
        ShopTopBar(
            cartCount = 3,
            wishlistCount = 5,
            onBackClick = { navHostController.navigate(Screen.Splash.route) },
            onSearchClick = { /* TODO */ },
            onWishlistClick = { /* TODO */ },
            onCartClick = { /* TODO */ }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(), // Changed from modifier to Modifier.fillMaxSize() to ensure column is fillable
            contentPadding = PaddingValues(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { PromoBanner() }

            item {
                CategoriesSection(category) { }
            }

            item {
                Text(
                    text = "New Products",
                    color = onBackground,
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            items(products) { product ->
                ProductItemCard(product) { clickedProduct ->
                    // On product item click, set the selected product and show the bottom sheet
                    selectedProduct = clickedProduct
                    showBottomSheet = true
                }
            }
        }
    }

    // Modal Bottom Sheet
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                // Hide the bottom sheet when dismissed
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        showBottomSheet = false
                        selectedProduct = null
                    }
                }
            },
            sheetState = sheetState,
            containerColor = background,
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            tonalElevation = 8.dp,
        ) {
            selectedProduct?.let { product ->
                ProductDetailsBottomSheetContent(product = product)
            }
        }
    }
}

// Update ProductItemCard to accept a click callback
@Composable
fun ProductItemCard(
    product: ProductModel,
    onProductClick: (ProductModel) -> Unit // New callback for clicks
) {
    Card(
        modifier = Modifier
            .height(570.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(10))
            .clickable { onProductClick(product) }, // Add clickable modifier
        colors = CardDefaults.cardColors(containerColor = background),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            // ... (rest of your ProductItemCard code remains the same) ...
            // Background Image
            Image(
                painter = painterResource(id = R.drawable.grey_card_svg),
                contentDescription = "Background Card Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )

            Column(modifier = Modifier.padding(12.dp)) {

                // Wishlist Icon & Badge
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Wishlist",
                        tint = Color(0xFFB5FF48)
                    )
                    Text(
                        text = "Best seller",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Color(0xFFB5FF48),
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier
                            .background(Color.Black, RoundedCornerShape(50))
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    )
                }

                // Product Image
                Image(
                    painter = painterResource(id = product.imageUrl),
                    contentDescription = product.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Product Info Background Box
                Box(modifier = Modifier.height(180.dp)) {
                    Image(
                        painter = painterResource(R.drawable.product_title_card),
                        contentDescription = "Product Info Background",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Column(modifier = Modifier.padding(16.dp)) {

                        // Product Title & Availability
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = product.name,
                                fontSize = 24.sp,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    color = Color(0xFFB5FF48),
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            if (product.inStock) {
                                Text(
                                    text = "● In stock",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Color(0xFFB5FF48)
                                    )
                                )
                            }else{
                                Text(
                                    text = "🔺 Out of stock",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Color(0xFFFF0F0F)
                                    )
                                )
                            }
                        }

                        // Product Description
                        Text(
                            text = product.description,
                            fontSize = 18.sp,
                            style = MaterialTheme.typography.labelSmall,
                            color = onBackground
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Pricing
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Rs. ${product.price}",
                                fontSize = 18.sp,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Color(0xFF6B5FFF),
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Rs. ${product.price}",
                                fontSize = 18.sp,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    textDecoration = TextDecoration.LineThrough,
                                    color = Color.Gray
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Ratings
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            repeat(5) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Rating Star",
                                    tint = Color(0xFFFFD700),
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "${product.reviews} reviews",
                                fontSize = 18.sp,
                                style = MaterialTheme.typography.labelSmall,
                                color = onBackground
                            )
                        }
                    }
                }
            }

            // Floating Cart Icon at Bottom-End
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 56.dp, end = 24.dp)
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFB5FF48)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Add to Cart",
                    tint = Color.Black,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }
}

// New Composable for the Bottom Sheet Content
@Composable
fun ProductDetailsBottomSheetContent(product: ProductModel) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            // Product Image at the top of the sheet
            Image(
                painter = painterResource(id = product.imageUrl),
                contentDescription = product.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)

            )
            Column (
                modifier = Modifier.padding(16.dp)
            ){

            Spacer(modifier = Modifier.height(24.dp)) // Increased space

            // Product Name
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp)) // Increased space

            // Product Description
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyLarge, // Changed to bodyLarge for better readability
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp)) // Increased space

            // Price and Reviews
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Rs. ${product.price}",
                    style = MaterialTheme.typography.titleLarge.copy( // Larger price text
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF6B5FFF)
                    )
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    repeat(5) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating Star",
                            tint = Color(0xFFFFD700),
                            modifier = Modifier.size(24.dp) // Slightly larger stars
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${product.reviews} reviews",
                        style = MaterialTheme.typography.bodyMedium, // Changed to bodyMedium
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp)) // Increased space before button

            // Add to Cart Button (example)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp) // Taller button
                    .clip(RoundedCornerShape(16.dp)) // More rounded button corners
                    .background(Color(0xFFB5FF48))
                    .clickable { /* Handle add to cart in bottom sheet */ },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Add to Cart",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }

        }
    }
}



@Preview(showBackground = true, backgroundColor = 0xFF2B3137)
@Composable
fun ShopScreenPreview() {
    ShopScreen(navHostController = rememberNavController())
}