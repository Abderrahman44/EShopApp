package com.abdat.eshop.ui.feature.product_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import coil.compose.rememberImagePainter
import com.abdat.eshop.model.UiProductModel
import com.abdat.eshop.ui.theme.KtorClientTheme
import com.abdat.ktorclient.R.*
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductDetailsScreen(
    navController: NavController,
    product: UiProductModel,
    viewModel: ProductDetailsVM = koinViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        ProductImage(product.image,onBackClick ={ navController.popBackStack()})
        ProductDetails(product.title,product.description,product.price.toString())
    }
}


@Composable
fun ProductImage(imageUrl: String, onBackClick:() -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        AsyncImage(
            model = imageUrl, // Replace with your image URL
            contentDescription = "Product Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        IconButton(
            onClick = { onBackClick },
            modifier = Modifier
                .padding(16.dp)
                .size(48.dp)
                .clip(CircleShape)
                .padding(8.dp)
                .align(Alignment.TopStart)
        ) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }
        IconButton(
            onClick = { /* Handle cart action */ },
            modifier = Modifier
                .padding(16.dp)
                .size(48.dp)
                .clip(CircleShape)
                .padding(8.dp)
                .align(Alignment.TopEnd)
        ) {
            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Cart")
        }

    }
}

@Composable
fun ProductDetails(title: String, details: String, price: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 20.sp)

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id =drawable.ic_star),
                contentDescription = "Star Icon",
                tint = Color(0xFFFFD700),
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "4.5", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "(20 Review)", color = Color.Gray, fontSize = 14.sp)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = price, color = Color(0xFF6200EE), fontWeight = FontWeight.Bold, fontSize = 18.sp)
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
            onClick = { /* Handle buy now action */ },
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



