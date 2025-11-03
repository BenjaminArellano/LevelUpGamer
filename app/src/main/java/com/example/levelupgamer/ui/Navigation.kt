package com.example.levelupgamer.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.levelupgamer.ui.screens.welcome.WelcomeScreen
import com.example.levelupgamer.ui.screens.login.LoginScreen
import com.example.levelupgamer.ui.screens.register.RegisterScreen
import com.example.levelupgamer.ui.screens.home.HomeScreen
import com.example.levelupgamer.ui.screens.producto.ProductoListScreen
import com.example.levelupgamer.ui.screens.producto.ProductoDetailScreen
import com.example.levelupgamer.ui.screens.carrito.CarritoScreen
import com.example.levelupgamer.viewmodel.UserViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.levelupgamer.ui.screens.producto.AgregarProductoScreen
import com.example.levelupgamer.ui.screens.welcome.NosotrosScreen
import com.example.levelupgamer.ui.screens.welcome.ContactoScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val userViewModel: UserViewModel = viewModel()

    NavHost(navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("login") { LoginScreen(navController, userViewModel) }
        composable("register") { RegisterScreen(navController, userViewModel) }
        composable("home") { HomeScreen(navController) }
        composable("productos") { ProductoListScreen(navController) }
        composable("agregarProducto") { AgregarProductoScreen(navController) }
        composable("nosotros") { NosotrosScreen(navController) }
        composable("contacto") { ContactoScreen(navController) }
        composable(
            route = "productoDetalle/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            ProductoDetailScreen(navController, id)
        }
        composable("carrito") { CarritoScreen(navController) }
    }
}
