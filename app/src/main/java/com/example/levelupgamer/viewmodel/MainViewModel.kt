package com.example.levelupgamer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelupgamer.model.Product
import com.example.levelupgamer.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// 1. Define UI State
sealed interface ProductUiState {
    data class Success(val products: List<Product>) : ProductUiState
    data object Error : ProductUiState
    data object Loading : ProductUiState
}

class MainViewModel : ViewModel() {

    // 2. Expose UI State using StateFlow
    private val _uiState = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    // 3. Fetch data on initialization
    init {
        fetchElectronics()
    }

    private fun fetchElectronics() {
        viewModelScope.launch {
            _uiState.value = ProductUiState.Loading
            try {
                val productList = RetrofitInstance.api.getElectronics()
                _uiState.value = ProductUiState.Success(productList)
            } catch (e: Exception) {
                _uiState.value = ProductUiState.Error
            }
        }
    }
}
