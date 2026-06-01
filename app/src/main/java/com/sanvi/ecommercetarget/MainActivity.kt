package com.sanvi.ecommercetarget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.ScrollableState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sanvi.ecommercetarget.navigation.Screens
import com.sanvi.ecommercetarget.screen.cart.View.CartScreen
import com.sanvi.ecommercetarget.screen.categories.View.CategoryScreen
import com.sanvi.ecommercetarget.screen.home.View.HomeScreen
import com.sanvi.ecommercetarget.screen.product.View.ProductDetailsScreen
import com.sanvi.ecommercetarget.screen.product.View.ProductScreen
import com.sanvi.ecommercetarget.screen.product.ViewModel.ProductViewModel
import com.sanvi.ecommercetarget.screen.profile.View.ProfileScreen
import com.sanvi.ecommercetarget.screen.signUp.View.SignUpScreen
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

//            EcommerceTargetTheme {
//                HomeScreen()
//            }

            // Navigation System
            print("Mukesh Branch")

            val navController = rememberNavController()

            // Nav Host  : Manage Navigation between Screen
            NavHost(
                navController = navController,
                startDestination = "Home"
            ){

                // define route using composable(){} for each screen
                composable(Screens.Home.route) {

                    HomeScreen(
                        navController = navController,
//                        onProfileClick = {navController.navigate("Profile")},
                        onProfileClick = {navController.navigate(Screens.Profile.route)},
                        onCartClick = {navController.navigate(Screens.Cart.route)}
                    )

                }

//                composable ("Cart"){
                composable (Screens.Cart.route){
                    CartScreen(navController = navController)
                }

                composable (Screens.Profile.route){
                    ProfileScreen(navController = navController, onSignOut = {})
                }

                composable (Screens.CategoryList.route){
                    CategoryScreen(navController)
                }

//                composable(Screens.ProductList.route) {
//                    val categoryId = it.arguments?.getString("categoryId")
//
//                    if (categoryId != null){
//                        ProductScreen(
//                            categoryId,
//                            navController = navController
//                        )
//                    }
//
//                }

                composable(Screens.ProductList.route) { backStackEntry ->
                    val categoryId = backStackEntry.arguments?.getString("categoryId")

                    if (categoryId != null) {
                        // Hilt will automatically provide the ViewModel
                        val productViewModel: ProductViewModel = hiltViewModel()
                        ProductScreen(
                            categoryId = categoryId,
                            navController = navController,
                            productViewModel = productViewModel
                        )
                    }
                }

                composable(Screens.ProductDetails.route){
                   val productId = it.arguments?.getString("productId")
                    if (productId != null){

                        ProductDetailsScreen(productId)

                    }

                }

                composable(Screens.SignUp.route){
                    SignUpScreen(
                        onNavigateToLogin = {
                            navController.navigate(Screens.Login.route)
                        },

                        onSignSuccess = {
                            navController.navigate(Screens.Home.route)
                        }

                    )

                }





            }



        }


    }
}

