// Archivo: ui/screens/login/LoginScreen.kt
package com.example.levelupgamer.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.levelupgamer.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController, vm: LoginViewModel = viewModel()) {
    val state by vm.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {

        Text(
            "Iniciar sesión",
            style = MaterialTheme.typography.titleLarge.copy(
                color = Color(0xFF39FF14),
                fontFamily = FontFamily.Default
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = state.username,
            onValueChange = vm::onUsernameChange,
            label = { Text("Usuario", color = Color(0xFF39FF14), fontFamily = FontFamily.Default) },
            textStyle = TextStyle(color = Color(0xFF39FF14), fontFamily = FontFamily.Default),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.password,
            onValueChange = vm::onPasswordChange,
            label = { Text("Contraseña", color = Color(0xFF39FF14), fontFamily = FontFamily.Default) },
            textStyle = TextStyle(color = Color(0xFF39FF14), fontFamily = FontFamily.Default),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black),
            singleLine = true
        )

        state.error?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { vm.submit { navController.navigate("home") } },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF39FF14),
                contentColor = Color.Black
            )
        ) {
            Text("Entrar", color = Color.Black)
        }
    }
}
