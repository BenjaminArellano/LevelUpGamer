package com.example.levelupgamer.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Clase de utilidad simulada para pruebas unitarias de lógica pura.
 * En un proyecto real, tendrías clases como esta para lógica de negocio
 * separada de la UI y frameworks.
 */
class CalculadoraDescuentos {
    fun calcularPrecioConDescuento(precio: Double, esSocio: Boolean): Double {
        return if (esSocio) {
            precio * 0.90 // 10% de descuento
        } else {
            precio
        }
    }
}

class CalculadoraDescuentosTest {

    private val calculadora = CalculadoraDescuentos()

    /**
     * PRUEBA 5: Lógica de descuentos para socios
     * 
     * EXPLICACIÓN:
     * Esta es una prueba unitaria pura. No depende de bases de datos ni ViewModels.
     * Verifica una regla de negocio específica: "Los socios tienen un 10% de descuento".
     */
    @Test
    fun `calcularPrecioConDescuento aplica 10 porciento si es socio`() {
        // GIVEN
        val precioOriginal = 10000.0
        val esSocio = true

        // WHEN
        val precioFinal = calculadora.calcularPrecioConDescuento(precioOriginal, esSocio)

        // THEN
        assertEquals(9000.0, precioFinal, 0.0)
    }

    /**
     * PRUEBA 6: Precio normal para no socios
     */
    @Test
    fun `calcularPrecioConDescuento mantiene precio si no es socio`() {
        // GIVEN
        val precioOriginal = 10000.0
        val esSocio = false

        // WHEN
        val precioFinal = calculadora.calcularPrecioConDescuento(precioOriginal, esSocio)

        // THEN
        assertEquals(10000.0, precioFinal, 0.0)
    }
}