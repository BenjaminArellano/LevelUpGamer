package com.example.levelupgamer.ui.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RegisterScreen(navController: NavController) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {

        Text(
            "Crear cuenta",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF39FF14),
            fontFamily = FontFamily.Default
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre", color = Color(0xFF39FF14), fontFamily = FontFamily.Default) },
            textStyle = LocalTextStyle.current.copy(color = Color(0xFF39FF14), fontFamily = FontFamily.Default),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo", color = Color(0xFF39FF14), fontFamily = FontFamily.Default) },
            textStyle = LocalTextStyle.current.copy(color = Color(0xFF39FF14), fontFamily = FontFamily.Default),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña", color = Color(0xFF39FF14), fontFamily = FontFamily.Default) },
            textStyle = LocalTextStyle.current.copy(color = Color(0xFF39FF14), fontFamily = FontFamily.Default),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate("login") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF39FF14),
                contentColor = Color.Black
            )
        ) {
            Text(
                "Registrar",
                fontFamily = FontFamily.Default
            )
        }
    }
}
