package com.sanvi.ecommercetarget.screen.categories.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanvi.ecommercetarget.screen.home.Model.Category
import com.sanvi.ecommercetarget.screen.utils.Firebase.FirebaseStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: FirebaseStoreRepository) : ViewModel() {

    // Encapsulation : mutable internally , Read only externally
    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories : StateFlow<List<Category>> get() = _categories

    init {
        fetchCategory()
    }

    private fun fetchCategory() {

        viewModelScope.launch {

            repository.getCategoriesFlow()
                .catch {
                    // handle error
                    print("error handle flow")
                }
                .collect { categories ->
                    // Each time new data is emitted when run this block

                    // Updated stateFlow with new data
                    _categories.value = categories

                    print("categories updated in viewmodel")
                }
        }

    }

}

