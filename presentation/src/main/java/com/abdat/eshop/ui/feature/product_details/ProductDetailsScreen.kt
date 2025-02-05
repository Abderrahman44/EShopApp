package com.abdat.eshop.ui.feature.product_details

import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.abdat.EshopApp.R.drawable
import com.abdat.domain.model.Product
import com.abdat.eshop.model.UiProductModel
import com.abdat.eshop.navigation.HomeScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductDetailsScreen(
    navController: NavController,
    product: UiProductModel,
    productDetailsVM: ProductDetailsVM = koinViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ProductImage(
            product.image,
            onBackClick = { navController.popBackStack() },
            onAddToCartClick = { productDetailsVM.addProductToCart(product) })
        ProductDetails(product.title, product.description, product.price.toString(),
            addOnCart = { productDetailsVM.addProductToCart(product) })
    }
    Box(modifier = Modifier.fillMaxSize()) {
        val uiState = productDetailsVM.state.collectAsState()
        val loading = remember {
            mutableStateOf(false)
        }
        LaunchedEffect(uiState.value) {
            when (uiState.value) {
                is ProductDetailsEvent.Loading -> {
                    // Show loading
                    loading.value = true
                }

                is ProductDetailsEvent.Success -> {
                    // Show success
                    loading.value = false
                    Toast.makeText(
                        navController.context,
                        (uiState.value as ProductDetailsEvent.Success).message,
                        Toast.LENGTH_LONG
                    ).show()
                }

                is ProductDetailsEvent.Error -> {
                    // Show error
                    Toast.makeText(
                        navController.context,
                        (uiState.value as ProductDetailsEvent.Error).message,
                        Toast.LENGTH_LONG
                    ).show()
                    loading.value = false
                }

                else -> {
                    loading.value = false
                }
            }
        }

        if (loading.value) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.7f)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
                Text(
                    text = "Adding to cart...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}


@Composable
fun ProductImage(
    imageUrl: String,
    onBackClick: () -> Unit,
    onAddToCartClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp),
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "Product Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        // Back Button
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .padding(16.dp)
                .size(40.dp) // Reduced size for better visual balance
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)) // Add a semi-transparent background
                .align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
              //  tint = Color.White // Ensure the icon is visible on any background
            )
        }
        // Add to Cart Button
        IconButton(
            onClick = { onAddToCartClick()},
            modifier = Modifier
                .padding(16.dp)
                .size(40.dp) // Reduced size for better visual balance
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)) // Add a semi-transparent background
                .align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "Add to Cart",
               // tint = Color.White // Ensure the icon is visible on any background
            )
        }
    }
}
@Composable
fun ProductDetails(title: String, details: String, price: String, addOnCart: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 20.sp)

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = drawable.ic_star),
                contentDescription = "Star Icon",
                tint = Color(0xFFFFD700),
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "4.5", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "(20 Review)", color = Color.Gray, fontSize = 14.sp)
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = price,
                color = Color(0xFF6200EE),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Description",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Text(
            text = details,
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Size", fontWeight = FontWeight.Bold, fontSize = 16.sp)

        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            SizeOption("8")
            SizeOption("10")
            SizeOption("38")
            SizeOption("40")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { addOnCart() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            //  colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(text = "Buy Now", color = Color.White, fontSize = 16.sp)
        }
    }
}

    @Composable
    fun SizeOption(size: String) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .padding(4.dp)
                .background(Color.LightGray, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = size, fontWeight = FontWeight.Bold)
        }
    }



