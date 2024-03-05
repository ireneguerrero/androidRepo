package com.examenfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: LibrosHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = LibrosHelper(this)

        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        val btnMostrar = findViewById<Button>(R.id.btnMostrar)
        val btnBorrar = findViewById<Button>(R.id.btnBorrar)
        val etTitulo = findViewById<EditText>(R.id.etTitulo)
        val etAutor = findViewById<EditText>(R.id.etAutor)
        val etGenero = findViewById<EditText>(R.id.etGenero)
        val etEditorial = findViewById<EditText>(R.id.etEditorial)
        val etAnio = findViewById<EditText>(R.id.etAnio)

        btnAgregar.setOnClickListener {
            val titulo = etTitulo.text.toString()
            val autor = etAutor.text.toString()
            val genero = etGenero.text.toString()
            val editorial = etEditorial.text.toString()
            val anio = etAnio.text.toString()
            val id = dbHelper.addLibro(
                Libro(
                    null,
                    titulo,
                    autor,
                    genero,
                    editorial,
                    anio
                )

            )
            mostrarMensaje("Producto agregado con ID: $id")

        }

        btnMostrar.setOnClickListener {
            val libro = dbHelper.getAllLibros()
            val stringBuilder = StringBuilder()
            for (dato in libro) {
                stringBuilder.append(
                    "ID: ${dato.id}, Título: ${dato.titulo}, " +
                            "Autor: ${dato.autor}, Género: ${dato.genero}, " +
                            "Editorial: ${dato.editorial}, Año: ${dato.anio}\n\n"
                )
            }
            mostrarMensaje(stringBuilder.toString())
        }

        btnBorrar.setOnClickListener {
            dbHelper.deleteAllLibros()
            mostrarMensaje("Tabla vacía")
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