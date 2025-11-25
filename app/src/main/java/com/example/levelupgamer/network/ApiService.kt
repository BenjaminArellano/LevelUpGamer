package com.example.levelupgamer.network

import com.example.levelupgamer.model.Product
import retrofit2.http.GET

interface ApiService {
    @GET("products/category/electronics")
    suspend fun getElectronics(): List<Product>
}