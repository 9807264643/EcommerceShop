package com.sanvi.ecommercetarget.screen.home.View

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.sanvi.ecommercetarget.navigation.BottomNavigationBar
import com.sanvi.ecommercetarget.navigation.Screens
import com.sanvi.ecommercetarget.screen.categories.ViewModel.CategoryViewModel
import com.sanvi.ecommercetarget.screen.home.Model.Category
import com.sanvi.ecommercetarget.screen.home.Model.Product
import com.sanvi.ecommercetarget.screen.product.ViewModel.ProductViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    onProfileClick : () -> Unit,
    onCartClick : () -> Unit,
    productViewModel: ProductViewModel = hiltViewModel(),
    categoryViewModel: CategoryViewModel = hiltViewModel()
    ) {

    Scaffold(
        topBar = { MyTopAppBar(onProfileClick,onCartClick) },

        bottomBar = { BottomNavigationBar() }

    ) { paddingValues ->

        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {

            // Search Section
            val searchQuery = remember { mutableStateOf("") }
            val focusManager = LocalFocusManager.current

            Spacer(modifier = Modifier.height(8.dp))

            SearchBar(
                query = searchQuery.value,
                onQueryChange = {searchQuery.value = it},

                onSearch = {
                    /* TODO */

                },
                modifier = Modifier.fillMaxWidth().padding(16.dp)

            )


            // Search Result Section

            // Categories Section
            SectionTitle("Categories","See All") {
                navController.navigate(Screens.CategoryList.route)
            }

            // mock the category
//            val categories: List<Category> = listOf(
//                Category(1,"Electronics","https://cdn-icons-png.flaticon.com/512/1555/1555401.png"),
//                Category(2,"Clothing","https://cdn-icons-png.flaticon.com/512/2935/2935183.png")
//            )

            // fetch data from firestore
            val categoriesState = categoryViewModel.categories.collectAsState()
            val categories = categoriesState.value

            // Selected Category name
            val selectedCategory = remember { mutableStateOf(0) }

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(categories.size) {

                    CategoryChip(
                        icon = categories[it].iconUrl,
                        text = categories[it].name,
                        isSelected = selectedCategory.value == it,

                        onClick = {
//                            selectedCategory.value = it
                            selectedCategory.value =categories[it].id

                            navController.navigate(
                                Screens.ProductList.createRoute(selectedCategory.value.toString())
                            )

                            Log.e("categoryId===>",categories[it].id.toString())

                        }
                    )

                }

            }


            //Feature Product Section
            Spacer(modifier = Modifier.height(16.dp))

            SectionTitle("Featured","See All") {

                navController.navigate(Screens.CategoryList.route)
            }

            // fetch data from firestore
            // fetch the products data when screen display first
            productViewModel.fetchAllProductsInFireStore()
            val allProducts = productViewModel.allProducts.collectAsState()
            val allProductsFoundList = allProducts.value

//                val allProductsFoundList  = listOf(
//                    Product("1","SmartPhone",0.2,"https://i.guim.co.uk/img/media/2ce8db064eabb9e22a69cc45a9b6d4e10d595f06/392_612_4171_2503/master/4171.jpg?width=1200&height=900&quality=85&auto=format&fit=crop&s=45b5856ba8cd83e6656fbe5c166951a4"),
//                    Product("2","Laptop",0.2,"https://cdn-dynmedia-1.microsoft.com/is/image/microsoftcorp/13-laptop-platinum-right-render-fy25:VP4-1260x795?fmt=png-alpha")
//                )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(allProductsFoundList){ product->

                    FeatureProductCard(product) {
                        /* handle click event here */
                        navController.navigate(Screens.ProductDetails.createRoute(product.id))
                    }

                }

            }


        }


    }

}