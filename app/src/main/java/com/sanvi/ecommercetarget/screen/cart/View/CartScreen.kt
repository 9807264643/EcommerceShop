package com.sanvi.ecommercetarget.screen.cart.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sanvi.ecommercetarget.screen.home.Model.Product

@Composable
fun CartScreen(navController: NavController){

    val cartItems = listOf(

            Product("3","SmartLaptop",999.99,"https://cdn-dynmedia-1.microsoft.com/is/image/microsoftcorp/13-laptop-platinum-right-render-fy25:VP4-1260x795?fmt=png-alpha")
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {

        Text(
            text = "Your Cart",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (cartItems.isEmpty()){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Your Cart is Empty",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { // TO DO
                         }

                ) {
                    Text(text = "Continue Shopping")
                }

            }


        }else{

            LazyColumn(modifier = Modifier.weight(1f)) {

               items(cartItems){ item ->
                   CartItemCard(
                       item = item,
                       onRemoveItem = {
                           //TO DO
                       }
                   )
               }

            }

          // Checkout Section and Total

            Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total: ",
                        style = MaterialTheme.typography.titleMedium,
                    )

                    // call viewmodel to get value
                    Text(
                        text = "$ ",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth().height(50.dp)
                ){
                        Text("Proceed to Checkout")
                 }


            }



        }

    }

}