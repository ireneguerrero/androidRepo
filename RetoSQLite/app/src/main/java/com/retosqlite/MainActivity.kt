package com.retosqlite

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: ProductosDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = ProductosDatabaseHelper(this)

        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        val btnMostrar = findViewById<Button>(R.id.btnMostrar)

        btnAgregar.setOnClickListener {
            // Agregar un nuevo producto
            val id = dbHelper.addProducto(
                Producto(
                    null,
                    "Pollo",
                    "Desplumao",
                    25.0,
                    10,
                    "Proveedor: Granja La Paqui",
                    "01/03/2024"
                )
            )
            mostrarMensaje("Producto agregado con ID: $id")
        }

        btnMostrar.setOnClickListener {
            // Mostrar todos los productos
            val productos = dbHelper.getAllProductos()
            val stringBuilder = StringBuilder()
            for (producto in productos) {
                stringBuilder.append("ID: ${producto.id_producto}, Nombre: ${producto.nombre}, " +
                        "Descripción: ${producto.descripcion}, Precio: ${producto.precio}, " +
                        "Cantidad: ${producto.cantidad}, Proveedor: ${producto.proveedor}, " +
                        "Fecha de Vencimiento: ${producto.fecha_vencimiento}\n\n")
            }
            mostrarMensaje(stringBuilder.toString())
        }
    }

    private fun mostrarMensaje(mensaje: String) {
        AlertDialog.Builder(this)
            .setMessage(mensaje)
            .setPositiveButton("Aceptar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
