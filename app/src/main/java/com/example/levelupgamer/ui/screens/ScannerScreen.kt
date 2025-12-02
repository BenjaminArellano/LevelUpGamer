package com.example.levelupgamer.ui.screens

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.zxing.integration.android.IntentIntegrator

@Composable
fun ScannerScreen() {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { activityResult ->
            val result = IntentIntegrator.parseActivityResult(activityResult.resultCode, activityResult.data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(context, "Escaneo cancelado", Toast.LENGTH_LONG).show()
                } else {
                    val scannedUrl = result.contents
                    Toast.makeText(context, "Redirigiendo a: $scannedUrl", Toast.LENGTH_LONG).show()

                    // Intentar abrir la URL en el navegador
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(scannedUrl))
                        context.startActivity(intent)
                    } catch (e: Exception) {
                        Toast.makeText(context, "Error al abrir el enlace: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            val integrator = IntentIntegrator(context as Activity)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            integrator.setPrompt("Escanea un código QR")
            integrator.setCameraId(0)
            integrator.setBeepEnabled(true)
            integrator.setBarcodeImageEnabled(true)
            launcher.launch(integrator.createScanIntent())
        }) {
            Text("Iniciar Escáner")
        }
    }
}