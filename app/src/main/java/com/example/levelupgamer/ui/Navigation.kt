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


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("productos") { ProductoListScreen(navController) }
        composable(
            route = "productoDetalle/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            ProductoDetailScreen(navController, id)
        }
    }
}
