package com.example.levelupgamer.ui.screens.carrito

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.levelupgamer.viewmodel.CarritoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoScreen(navController: NavController) {
    val carritoViewModel: CarritoViewModel = viewModel()
    val productosCarrito by carritoViewModel.productosCarrito.collectAsState()
    val totalCarrito by carritoViewModel.totalCarrito.collectAsState()

    Scaffold(
        modifier = Modifier.background(Color.Black),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Mi Carrito",
                        color = Color(0xFF39FF14),
                        fontFamily = FontFamily.Default
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Regresar",
                            tint = Color(0xFF39FF14)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black
                )
            )
        },
        bottomBar = {
            if (productosCarrito.isNotEmpty()) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    color = Color.DarkGray,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Total:",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color(0xFF39FF14),
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                "$${String.format("%.2f", totalCarrito)}",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color(0xFF39FF14),
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                // Aquí puedes agregar la lógica para finalizar compra
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF39FF14),
                                contentColor = Color.Black
                            )
                        ) {
                            Text("Finalizar Compra", fontWeight = FontWeight.Bold)
                        }
                        TextButton(
                            onClick = { carritoViewModel.limpiarCarrito() },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "Limpiar Carrito",
                                color = Color(0xFF1E90FF)
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        if (productosCarrito.isEmpty()) {
            // Carrito vacío
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color.Black)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Default.ShoppingCart,
                    contentDescription = "Carrito vacío",
                    tint = Color(0xFF39FF14),
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Tu carrito está vacío",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF39FF14)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Agrega algunos productos desde la lista",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFFD3D3D3)
                )
            }
        } else {
            // Lista de productos en el carrito
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color.Black)
                    .padding(16.dp)
            ) {
                items(productosCarrito) { producto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    producto.nombre,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color(0xFF39FF14),
                                    fontFamily = FontFamily.Default
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    producto.descripcion,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color(0xFFD3D3D3),
                                    fontFamily = FontFamily.Default
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    "$${producto.precio}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color(0xFF1E90FF),
                                    fontFamily = FontFamily.Default,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            IconButton(
                                onClick = { carritoViewModel.eliminarDelCarrito(producto) }
                            ) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = "Eliminar",
                                    tint = Color.Red
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}