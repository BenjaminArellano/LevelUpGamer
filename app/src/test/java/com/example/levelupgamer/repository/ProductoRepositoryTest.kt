package com.example.levelupgamer.repository

import com.example.levelupgamer.data.dao.ProductoDao
import com.example.levelupgamer.data.model.Producto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ProductoRepositoryTest {

    // Mock del DAO: Simulamos el objeto de acceso a datos
    private val productoDao = mockk<ProductoDao>()
    
    // Repositorio bajo prueba

    /**
     * PRUEBA 3: Obtener productos desde la base de datos
     */
    @Test
    fun `getAllProductos debe retornar lista de productos`() = runTest {
        // GIVEN (DADO): Una lista de productos simulada
        val productosFake = listOf(
            Producto(1, "PS5", "Consola", 500000.0),
            Producto(2, "Xbox", "Consola", 450000.0)
        )
        
        // Configuramos el mock: Cuando se llame a getAllProductos, devuelve un Flow con nuestra lista
        coEvery { productoDao.getAllProductos() } returns flowOf(productosFake)

        // WHEN (CUANDO): Llamamos al método del DAO
        var resultado: List<Producto> = emptyList()
        productoDao.getAllProductos().collect {
            resultado = it
        }

        // THEN (ENTONCES): Verificamos que el resultado sea igual a lo esperado
        assertEquals(2, resultado.size)
        assertEquals("PS5", resultado[0].nombre)
    }

    /**
     * PRUEBA 4: Insertar un producto
     */
    @Test
    fun `insert debe llamar al dao con el producto correcto`() = runTest {
        // GIVEN
        val nuevoProducto = Producto(nombre = "Nintendo Switch", descripcion = "Híbrida", precio = 300000.0)
        // Configuramos el mock para que no haga nada (just runs) cuando se llame a insert
        coEvery { productoDao.insert(any()) } returns Unit

        // WHEN
        productoDao.insert(nuevoProducto)

        // THEN
        // Verificamos (coVerify) que el método insert fue llamado exactamente una vez con nuestro producto
        coVerify(exactly = 1) { productoDao.insert(nuevoProducto) }
    }
}