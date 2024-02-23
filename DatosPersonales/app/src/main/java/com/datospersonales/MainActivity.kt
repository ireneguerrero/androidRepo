package com.datospersonales

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatosPersonalesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatosPersonalesHelper(this)

        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        val btnMostrar = findViewById<Button>(R.id.btnMostrar)
        val btnBorrar = findViewById<Button>(R.id.btnBorrar)
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etApellidos = findViewById<EditText>(R.id.etApellidos)
        val etDireccion = findViewById<EditText>(R.id.etDireccion)
        val etCp = findViewById<EditText>(R.id.etCp)
        val etCiudad = findViewById<EditText>(R.id.etCiudad)
        val etProvincia = findViewById<EditText>(R.id.etProvincia)
        val etTelefono = findViewById<EditText>(R.id.etTelefono)

        btnAgregar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val apellidos = etApellidos.text.toString()
            val direccion = etDireccion.text.toString()
            val cp = etCp.text.toString()
            val ciudad = etCiudad.text.toString()
            val provincia = etProvincia.text.toString()
            val telefono = etTelefono.text.toString()
            val id = dbHelper.addDato(
                DatoPersonal(
                    null,
                    nombre,
                    apellidos,
                    direccion,
                    cp,
                    ciudad,
                    provincia,
                    telefono
                )

            )
            mostrarMensaje("Producto agregado con ID: $id")

        }

        btnMostrar.setOnClickListener {
            val datoPersonal = dbHelper.getAllDatos()
            val stringBuilder = StringBuilder()
            for (dato in datoPersonal) {
                stringBuilder.append(
                    "ID: ${dato.id_persona}, Nombre: ${dato.nombre}, " +
                            "Apellidos: ${dato.apellidos}, Direccion: ${dato.direccion}, " +
                            "CP: ${dato.cp}, Ciudad: ${dato.ciudad}, " +
                            "Provincia: ${dato.provincia}, Teléfono: ${dato.telefono}\n\n"
                )
            }
            mostrarMensaje(stringBuilder.toString())
        }

        btnBorrar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val datoBorrar = dbHelper.deleteDato(nombre)
            if (datoBorrar > 0) {
                Toast.makeText(
                    this,
                    "$nombre se eliminó correctamente",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "No existe en la base de datos",
                    Toast.LENGTH_LONG
                ).show()
            }

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