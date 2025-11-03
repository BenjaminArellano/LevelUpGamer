package com.example.levelupgamer.ui.screens.producto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.levelupgamer.viewmodel.CarritoViewModel
import com.example.levelupgamer.viewmodel.ProductoViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductoListScreen(navController: NavController) {
    val productoViewModel: ProductoViewModel = viewModel()
    val carritoViewModel: CarritoViewModel = viewModel()
    val productos by productoViewModel.productos.collectAsState()
    val cantidadCarrito = carritoViewModel.getCantidadTotal()

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(200.dp).background(Color.Black)) {

                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "Menú",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color(0xFF39FF14),
                        fontFamily = FontFamily.Default
                    ),
                    modifier = Modifier.padding(16.dp)
                )

                HorizontalDivider(color = Color.Gray, thickness = 1.dp)

                NavigationDrawerItem(
                    label = { Text("Inicio", color = Color(0xFF39FF14), fontFamily = FontFamily.Default) },
                    selected = false,
                    onClick = {
                        navController.navigate("home")
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )

                NavigationDrawerItem(
                    label = { Text("Productos", color = Color(0xFF39FF14), fontFamily = FontFamily.Default) },
                    selected = true,
                    onClick = {
                        navController.navigate("productos")
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )

                NavigationDrawerItem(
                    label = { Text("Sobre Nosotros", color = Color(0xFF39FF14), fontFamily = FontFamily.Default) },
                    selected = false,
                    onClick = {
                        navController.navigate("nosotros")
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )

                NavigationDrawerItem(
                    label = { Text("Contacto", color = Color(0xFF39FF14), fontFamily = FontFamily.Default) },
                    selected = false,
                    onClick = {
                        navController.navigate("contacto")
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )

                NavigationDrawerItem(
                    label = {
                        Row {
                            Text("Carrito", color = Color(0xFF39FF14), fontFamily = FontFamily.Default)
                            if (cantidadCarrito > 0) {
                                Spacer(modifier = Modifier.width(8.dp))
                                Badge {
                                    Text(cantidadCarrito.toString(), color = Color.White)
                                }
                            }
                        }
                    },
                    selected = false,
                    onClick = {
                        navController.navigate("carrito")
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            modifier = Modifier.background(Color.Black),
            topBar = {
                TopAppBar(
                    title = { Text("Lista de Productos", color = Color(0xFF39FF14), fontFamily = FontFamily.Default) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Abrir Menú", tint = Color(0xFF39FF14))
                        }
                    },
                    actions = {
                        IconButton(onClick = { navController.navigate("carrito") }) {
                            Box {
                                Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito", tint = Color(0xFF39FF14))
                                if (cantidadCarrito > 0) {
                                    Badge(modifier = Modifier.align(Alignment.TopEnd)) {
                                        Text(cantidadCarrito.toString(), color = Color.White)
                                    }
                                }
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Black
                    )
                )
            }
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color.Black)
                    .padding(16.dp)
            ) {

                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(productos) { producto ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
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
                                    color = Color(0xFF39FF14),
                                    fontFamily = FontFamily.Default
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    "$${producto.precio}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color(0xFF39FF14),
                                    fontFamily = FontFamily.Default
                                )
                                Spacer(modifier = Modifier.height(8.dp))

                                Button(
                                    onClick = { carritoViewModel.agregarAlCarrito(producto) },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF1E90FF),
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text("Agregar al Carrito")
                                }

                                TextButton(
                                    onClick = { navController.navigate("productoDetalle/${producto.id}") },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Ver Detalles", color = Color(0xFF39FF14))
                                }
                            }
                        }
                    }
                }

                // ----------------- BOTÓN AGREGAR PRODUCTO -----------------
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navController.navigate("agregarProducto") }, // Asegúrate de tener esta ruta en AppNavigation
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF39FF14),
                        contentColor = Color.Black
                    )
                ) {
                    Text("Agregar Producto")
                }
            }
        }
    }
}
