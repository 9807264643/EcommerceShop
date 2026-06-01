package com.sanvi.ecommercetarget.screen.product.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.rememberAsyncImagePainter
import com.sanvi.ecommercetarget.screen.home.Model.Product
import com.sanvi.ecommercetarget.screen.product.ViewModel.ProductViewModel

@Composable
fun ProductDetailsScreen(
    productId : String,
    productViewModel : ProductViewModel = hiltViewModel()
){

    // fetch the product detail when screen is first display
    LaunchedEffect(productId) {
        productViewModel.fetchProductDetail(productId)
    }


    // collect product details data from viewmodel
//    val myDummyProduct = Product(
//        "1",
//        "SmartPhone",
//        9999.99,
//        "https://i.guim.co.uk/img/media/2ce8db064eabb9e22a69cc45a9b6d4e10d595f06/392_612_4171_2503/master/4171.jpg?width=1200&height=900&quality=85&auto=format&fit=crop&s=45b5856ba8cd83e6656fbe5c166951a4",
//
//    )

    val productState = productViewModel.product.collectAsState()
    val product = productState.value


    if (product == null){
        Text(
            text = "Product Record No Found",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
    }else{

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {

            Image(painter = rememberAsyncImagePainter(
                model = product.imageUrl
            ),
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .height(300.dp)
                    .clip(RoundedCornerShape(12.dp))

            )

            Spacer(modifier = Modifier.height(16.dp))

            // Product Name
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Product Price

            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))


            // Product Description

            Text(
                text = product.id ?: "Product Descriptions Not Found",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

        }

        IconButton(
            onClick = {}, // handle on add to cart
            modifier = Modifier.padding(16.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
        ) {

            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Shop Cart",
                tint = Color.White
            )

        }

    }



}