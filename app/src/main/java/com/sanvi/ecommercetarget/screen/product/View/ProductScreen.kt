package com.sanvi.ecommercetarget.screen.product.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sanvi.ecommercetarget.navigation.Screens
import com.sanvi.ecommercetarget.screen.home.Model.Product
import com.sanvi.ecommercetarget.screen.product.ViewModel.ProductViewModel

@Composable
fun ProductScreen(
    categoryId : String,
    navController: NavController,
    productViewModel: ProductViewModel
){

    // fetch Product data from viewmodel
//    val products = listOf<Product>(
//        Product("1","SmartLaptop",999.99,"https://cdn-dynmedia-1.microsoft.com/is/image/microsoftcorp/13-laptop-platinum-right-render-fy25:VP4-1260x795?fmt=png-alpha"),
//        Product("2","Lenovo",2999.99,"https://cdn.thewirecutter.com/wp-content/media/2026/04/BEST-LAPTOPS-PHOTO-VIDEO-EDITING-DAV0597.jpg?width=2048&quality=60&crop=2048:1365&auto=webp"),
//        Product("3","Apple",1999.99,"https://cdn.mos.cms.futurecdn.net/w3woQuKuGufGWfGeZ6Je3C-1200-80.jpg"),
//
//
//    )

    LaunchedEffect(categoryId) {
        productViewModel.fetchProducts(categoryId)
    }

    // collect products from viewmodel
   val productState = productViewModel.products.collectAsState()
    val products = productState.value


    // Display the product
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text = "Product of categoryId : ${categoryId}",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )

        // if product is not found
        if (products.isEmpty()){
            Text(
                text = "Product record no found",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(20.dp)
            )
        }else{

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(products){ product ->
                    ProductItem(
                        product = product,
                        onClick = {

                            // navigate to productDetailScree with productId
                            navController.navigate(Screens.ProductDetails.createRoute(product.id))
                        },

                        onAddToCart = {}
                        )

                }
            }
        }

    }

}