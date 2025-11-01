package com.example.levelupgamer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelupgamer.data.database.ProductoDatabase
import com.example.levelupgamer.data.model.Producto
import com.example.levelupgamer.data.repository.ProductoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProductoViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = ProductoDatabase.getDatabase(application).productoDao()
    private val repository = ProductoRepository(dao)

    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos.asStateFlow()

    init {
        // Obtenemos los productos de la base de datos
        viewModelScope.launch {
            repository.getAllProductos().collect { lista ->
                _productos.value = lista
            }
        }
    }

    // Función para agregar un producto
    fun agregarProducto(producto: Producto) {
        viewModelScope.launch {
            repository.insert(producto)
        }
    }
}
