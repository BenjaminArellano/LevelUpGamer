package com.example.levelupgamer.ui.screens.welcome

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.junit.Rule
import org.junit.Test

/**
 * PRUEBA INSTRUMENTADA (ANDROID TEST)
 * 
 * EXPLICACIÓN:
 * Esta prueba se ejecuta en un dispositivo Android real o emulador.
 * Verifica que la interfaz gráfica (UI) de la pantalla de Bienvenida
 * se muestre correctamente y responda a las interacciones.
 */
class WelcomeScreenTest {

    // Regla que configura el entorno de prueba de Compose
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun welcomeScreen_muestraTextoCorrecto() {
        // GIVEN: Cargamos la pantalla de bienvenida
        composeTestRule.setContent {
            val navController = rememberNavController()
            // Configuramos un NavHost básico para que el NavController tenga un contexto válido
            NavHost(navController = navController, startDestination = "welcome") {
                composable("welcome") { WelcomeScreen(navController = navController) }
                composable("login") { }
                composable("register") { }
            }
        }

        // THEN: Verificamos que los textos principales estén visibles
        
        // 1. Verifica el título principal
        composeTestRule
            .onNodeWithText("¡Bienvenido a LevelUP-Gamer!")
            .assertIsDisplayed()

        // 2. Verifica el subtítulo
        composeTestRule
            .onNodeWithText("Tu tienda gamer favorita")
            .assertIsDisplayed()
    }

    @Test
    fun welcomeScreen_botonesVisibles() {
        // GIVEN: Cargamos la pantalla
        composeTestRule.setContent {
            val navController = rememberNavController()
            // SOLUCIÓN DEL ERROR:
            // Envolvemos WelcomeScreen en un NavHost y definimos las rutas ("login", "register")
            // para que cuando performClick() active la navegación, el NavController encuentre el destino.
            NavHost(navController = navController, startDestination = "welcome") {
                composable("welcome") { WelcomeScreen(navController = navController) }
                composable("login") { } // Destino vacío para simular la navegación
                composable("register") { } // Destino vacío para simular la navegación
            }
        }

        // THEN: Verificamos que los botones existan y sean visibles
        
        // ERROR ANTERIOR: Al hacer click en "Iniciar Sesión", la app navegaba a la pantalla "login" (vacía),
        // haciendo que la pantalla "welcome" desapareciera. Por lo tanto, cuando intentaba buscar
        // "Registrarse" después del click, fallaba porque el botón ya no existía en la UI.
        
        // CORRECCIÓN: Verificamos la visibilidad de AMBOS botones ANTES de hacer click.

        // 1. Botón de Iniciar Sesión visible
        composeTestRule
            .onNodeWithText("Iniciar Sesión")
            .assertIsDisplayed()

        // 2. Botón de Registrarse visible
        composeTestRule
            .onNodeWithText("Registrarse")
            .assertIsDisplayed()
            
        // 3. Verificamos interacción (Hacer click navega y cambia la pantalla, por eso va al final)
        composeTestRule
            .onNodeWithText("Iniciar Sesión")
            .performClick()
    }
}