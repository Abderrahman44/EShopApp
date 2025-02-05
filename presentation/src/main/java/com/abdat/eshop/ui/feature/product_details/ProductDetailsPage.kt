//package com.abdat.eshop.ui.feature.product_details
//
//
//import android.widget.Toast
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material.icons.filled.ShoppingCart
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.IconButtonColors
//import androidx.compose.material3.IconButtonDefaults
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import coil.compose.AsyncImage
//import com.abdat.EshopApp.R
//import com.abdat.eshop.model.UiProductModel
//import org.koin.androidx.compose.koinViewModel
//
//@Composable
//fun ProductDetailsPage(
//    navController: NavController,
//    product: UiProductModel,
//    viewModel: ProductDetailsVM = koinViewModel(),
//) {
//    // Use a single loading state for the entire page
//    var isLoading by remember { mutableStateOf(false) }
//
//    // Collect the state once at the top level
//    val uiState by viewModel.state.collectAsState()
//
//    // Handle state changes
//    LaunchedEffect(uiState) {
//        when (uiState) {
//            is ProductDetailsEvent.Loading -> isLoading = true
//            is ProductDetailsEvent.Success -> {
//                isLoading = false
//                Toast.makeText(
//                    navController.context,
//                    (uiState as ProductDetailsEvent.Success).message,
//                    Toast.LENGTH_LONG
//                ).show()
//            }
//
//            is ProductDetailsEvent.Error -> {
//                isLoading = false
//                Toast.makeText(
//                    navController.context,
//                    (uiState as ProductDetailsEvent.Error).message,
//                    Toast.LENGTH_LONG
//                ).show()
//            }
//
//            ProductDetailsEvent.Nothing -> {}
//        }
//    }
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
//        ) {
//            ProductImage(
//                imageUrl = product.image,
//                onBackClick = { navController.popBackStack() },
//                onAddToCartClick = { viewModel.addProductToCart(product) }
//            )
//            ProductDetailsContent(
//                product = product,
//                onAddToCart = { viewModel.addProductToCart(product) })
//        }
//
//        // Show loading overlay if needed
//        if (isLoading) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.Black.copy(alpha = 0.7f)),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//                CircularProgressIndicator()
//                Text(
//                    text = "Adding to cart...",
//                    style = MaterialTheme.typography.bodyMedium,
//                    color = MaterialTheme.colorScheme.onPrimary
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun ProductDetailsContent(product: UiProductModel, onAddToCart: () -> Unit) {
//    Column(modifier = Modifier.fillMaxWidth()) {
//        ProductTitleAndPrice(title = product.title, price = product.price)
//        ProductRatingAndReviews()
//        ProductDescription(description = product.description)
//        ProductSizes()
//        AddToCartButtons(onAddToCart = onAddToCart)
//    }
//}
//
//@Composable
//fun ProductTitleAndPrice(title: String, price: Double) {
//    Row(modifier = Modifier.fillMaxWidth()) {
//        Text(
//            text = title,
//            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
//            modifier = Modifier
//                .padding(16.dp)
//                .weight(1f)
//        )
//        Text(
//            text = "$$price",
//            style = MaterialTheme.typography.titleMedium,
//            modifier = Modifier.padding(16.dp),
//            color = MaterialTheme.colorScheme.primary
//        )
//    }
//}
//
//@Composable
//fun ProductRatingAndReviews() {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 16.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Image(painter = painterResource(id = R.drawable.ic_star), contentDescription = null)
//        Spacer(modifier = Modifier.size(4.dp))
//        Text(
//            text = "4.5",
//            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
//        )
//        Spacer(modifier = Modifier.size(16.dp))
//        Text(
//            text = "(10 Reviews)",
//            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
//            color = Color.Gray
//        )
//    }
//}
//
//@Composable
//fun ProductDescription(description: String) {
//    Spacer(modifier = Modifier.size(16.dp))
//    Text(
//        text = "Description",
//        style = MaterialTheme.typography.titleMedium,
//        modifier = Modifier.padding(start = 16.dp)
//    )
//    Spacer(modifier = Modifier.size(8.dp))
//    Text(
//        text = description,
//        style = MaterialTheme.typography.bodySmall,
//        minLines = 3,
//        maxLines = 6,
//        modifier = Modifier.padding(horizontal = 16.dp),
//        color = Color.Gray
//    )
//}
//
//@Composable
//fun ProductSizes() {
//    Spacer(modifier = Modifier.size(16.dp))
//    Text(
//        text = "Size",
//        style = MaterialTheme.typography.titleMedium,
//        modifier = Modifier.padding(start = 16.dp)
//    )
//    Spacer(modifier = Modifier.size(8.dp))
//        Row(modifier = Modifier.padding(vertical = 8.dp)) {
//            SizeOption("8")
//            SizeOption("10")
//            SizeOption("38")
//            SizeOption("40")
//        }
//
//}
//
//
//@Composable
//fun AddToCartButtons(onAddToCart: () -> Unit) {
//    Spacer(modifier = Modifier.size(16.dp))
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 16.dp)
//    ) {
//        Button(
//            onClick = onAddToCart,
//            modifier = Modifier.weight(1f)
//        ) {
//            Text(text = "Buy Now")
//        }
//        Spacer(modifier = Modifier.size(8.dp))
//        IconButton(
//            onClick = onAddToCart,
//            colors = IconButtonDefaults.iconButtonColors()
//                .copy(containerColor = Color.LightGray.copy(alpha = 0.4f))
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.ic_cart),
//                contentDescription = "Add to Cart"
//            )
//        }
//    }
//}
//
