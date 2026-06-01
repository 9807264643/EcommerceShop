package com.sanvi.ecommercetarget.screen.categories.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.sanvi.ecommercetarget.navigation.Screens
import com.sanvi.ecommercetarget.screen.categories.ViewModel.CategoryViewModel
import com.sanvi.ecommercetarget.screen.home.Model.Category

@Composable
fun CategoryScreen(
    navController: NavController,
    categoryViewModel: CategoryViewModel = hiltViewModel()
){

//    val categories: List<Category> = listOf(
//        Category(1,"Electronics","https://cdn-icons-png.flaticon.com/512/1555/1555401.png"),
//        Category(2,"Clothing","https://cdn-icons-png.flaticon.com/512/2935/2935183.png")
//    )

    val categoriesState = categoryViewModel.categories.collectAsState()
    val categories = categoriesState.value

    Column {

        if (categories.isEmpty()){
            Box(modifier = Modifier.fillMaxSize().padding(16.dp),
                contentAlignment = Alignment.Center) {

                Text(
                    text = "No Categories Found",
                    style = MaterialTheme.typography.displayLarge
                )
            }

        }else {

            // Categories Tittle
            Text(
                text = "Categories",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),

            )

            // Categories Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            ) {
                items(categories){ category ->

                    CategoriesItem(
                        category = category,
                        onClick = {

                            navController.navigate(Screens.ProductList.createRoute(category.id.toString()))

                        }

                    )

                }

            }


        }
    }


}