package com.example.levelupgamer.ui.screens.home

import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.scale
import com.example.levelupgamer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .width(220.dp)
                    .background(Color.Black)
            ) {

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    "Menú",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color(0xFF39FF14),
                        fontFamily = FontFamily.Default
                    ),
                    modifier = Modifier.padding(16.dp)
                )

                HorizontalDivider(color = Color.DarkGray, thickness = 1.dp)

                NavigationDrawerItem(
                    label = { Text("Inicio", color = Color(0xFF39FF14), fontFamily = FontFamily.Default) },
                    selected = true,
                    onClick = {
                        navController.navigate("home")
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )

                NavigationDrawerItem(
                    label = { Text("Productos", color = Color(0xFF39FF14), fontFamily = FontFamily.Default) },
                    selected = false,
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
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Bienvenido") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Abrir menú")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Black,
                        titleContentColor = Color(0xFF39FF14),
                        navigationIconContentColor = Color(0xFF39FF14)
                    )
                )
            }
        ) { paddingValues ->
            // Contenedor principal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.Black),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    "¡Bienvenido a LevelUpGamer!",
                    color = Color(0xFF39FF14),
                    fontFamily = FontFamily.Default,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(28.dp))

                // Animación de "respiración"
                val infiniteTransition = rememberInfiniteTransition()
                val scale by infiniteTransition.animateFloat(
                    initialValue = 1f,
                    targetValue = 1.14f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(1200, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Reverse
                    )
                )

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo LevelUpGamer",
                    modifier = Modifier
                        .size(150.dp)
                        .scale(scale)
                )
            }
        }
    }
}
