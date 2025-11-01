package com.example.levelupgamer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelupgamer.data.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CarritoViewModel : ViewModel() {

    private val _productosCarrito = MutableStateFlow<List<Producto>>(emptyList())
    val productosCarrito: StateFlow<List<Producto>> = _productosCarrito.asStateFlow()

    private val _totalCarrito = MutableStateFlow(0.0)
    val totalCarrito: StateFlow<Double> = _totalCarrito.asStateFlow()

    private val _cantidadCarrito = MutableStateFlow(0)
    val cantidadCarrito: StateFlow<Int> = _cantidadCarrito.asStateFlow()

    fun agregarAlCarrito(producto: Producto) {
        viewModelScope.launch {
            _productosCarrito.value = _productosCarrito.value + producto
            calcularTotal()
            _cantidadCarrito.value = _productosCarrito.value.size // Actualizamos la cantidad
        }
    }

    fun eliminarDelCarrito(producto: Producto) {
        viewModelScope.launch {
            _productosCarrito.value = _productosCarrito.value.filter { it.id != producto.id }
            calcularTotal()
            _cantidadCarrito.value = _productosCarrito.value.size // Actualizamos la cantidad
        }
    }

    fun limpiarCarrito() {
        viewModelScope.launch {
            _productosCarrito.value = emptyList()
            calcularTotal()
            _cantidadCarrito.value = 0 // Actualizamos la cantidad
        }
    }

    private fun calcularTotal() {
        _totalCarrito.value = _productosCarrito.value.sumOf { it.precio }
    }

}