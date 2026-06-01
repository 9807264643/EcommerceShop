package com.sanvi.ecommercetarget.navigation

sealed class Screens(val route : String) {

    // Define screen routes for navigation in compose
    //Each object represent screen in app nav graph

    object Cart : Screens("Cart")

    object ProductDetails : Screens("product_details/{productId}") {
        fun createRoute(productId : String) = "Product_details/${productId}"
    }

    object Profile : Screens("Profile")

    object ProductList : Screens("product_list/{categoryId}") {
        fun createRoute(categoryId : String) = "product_list/${categoryId}"
    }

    object CategoryList : Screens("category_list")

    object Login : Screens("Login")

    object SignUp : Screens("SignUP")

    object Home : Screens("Home")

}