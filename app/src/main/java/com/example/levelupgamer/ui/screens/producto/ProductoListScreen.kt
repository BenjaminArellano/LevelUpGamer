package com.example.levelupgamer.ui.screens.producto

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.levelupgamer.viewmodel.ProductoViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductoListScreen(navController: NavController) {
    val viewModel: ProductoViewModel = viewModel()
    val productos by viewModel.productos.collectAsState()

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(200.dp) .background(Color.Gray)) {

                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "Menú",
                    style = MaterialTheme.typography.titleMedium.copy(color = Color(0xFF39FF14), fontFamily = FontFamily.Default),
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
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Black
                    )
                )
            }
        ) { innerPadding ->

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color.Black)
                    .padding(16.dp)
            ) {
                items(productos) { producto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable {
                                navController.navigate("productoDetalle/${producto.id}")
                            },
                        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(producto.nombre, style = MaterialTheme.typography.titleMedium, color = Color(0xFF39FF14), fontFamily = FontFamily.Default)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(producto.descripcion, style = MaterialTheme.typography.bodyMedium, color = Color(0xFF39FF14), fontFamily = FontFamily.Default)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("$${producto.precio}", style = MaterialTheme.typography.bodyLarge, color = Color(0xFF39FF14), fontFamily = FontFamily.Default)
                        }
                    }
                }
            }
        }
    }
}
