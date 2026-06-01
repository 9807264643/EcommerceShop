package com.sanvi.ecommercetarget.screen.product.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanvi.ecommercetarget.screen.home.Model.Product
import com.sanvi.ecommercetarget.screen.utils.Firebase.FirebaseStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: FirebaseStoreRepository) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products : StateFlow<List<Product>> get() = _products

    fun fetchProducts(categoryId : String) {

        viewModelScope.launch {
            try {
                val products = repository.getProductsByCategory(categoryId)
                _products.value = products

            }catch (e: Exception){
                Log.e("Tag Product","Error fetching products : ${e.message}")

            }
        }

    }


    private val _allProducts = MutableStateFlow<List<Product>>(emptyList())
    val allProducts : StateFlow<List<Product>> get() = _allProducts

    fun fetchAllProductsInFireStore() {

        viewModelScope.launch {
            try {
                val allProducts = repository.getAllProductsInFireStore()
                _allProducts.value = allProducts

            }catch (e: Exception){
                Log.e("Tag Product","Error fetching products : ${e.message}")

            }
        }

    }

    private val _product = MutableStateFlow<Product?>(null)
    val product : StateFlow<Product?> get() = _product

    fun fetchProductDetail(productId: String){

        viewModelScope.launch {
            try {
                val product = repository.getProductById(productId)
                _product.value = product

            }catch (e: Exception){
                Log.e("Tag Product","Error fetching product : ${e.message}")

            }
        }

    }


}