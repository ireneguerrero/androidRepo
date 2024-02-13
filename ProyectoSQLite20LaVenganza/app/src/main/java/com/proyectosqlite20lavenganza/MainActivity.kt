package com.proyectosqlite20lavenganza

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*el siguiente bloque maneja lo que ocurre al tocar
         *el boton Agregar */
        //definimos variables de elementos del actitivy
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        val btnTodos = findViewById<Button>(R.id.btnTodos)
        val etNombre = findViewById<TextView>(R.id.etNombre)
        val tvMuestraNombre = findViewById<TextView>(R.id.tvMuestraNombre)


        btnAgregar.setOnClickListener {
            // Crear una instancia de AyudaDb, que es la clase que maneja la base de datos SQLite
            val dbHandler = AyudaDB(this, null)

            // Obtener el nombre del cuadro de texto (etNombre) y crear un objeto Nombre con él
            val user = Nombre(etNombre.text.toString())

            // Agregar el nombre a la base de datos utilizando el método addName del ayudante de la base de datos
            dbHandler.addName(user)

            // Mostrar un mensaje de Toast indicando que el nombre se agregó a la base de datos
            Toast.makeText(
                this,
                "Se agregó a la base de datos a: " + etNombre.text.toString(),
                Toast.LENGTH_LONG
            ).show()

            // Limpiar el cuadro de texto después de agregar el nombre
            etNombre.setText("\n")
        }

        /*el siguiente bloque maneja lo que ocurre al tocar
        * el boton Mostrar Todos*/
        btnTodos.setOnClickListener {
            tvMuestraNombre.text = "" // Limpia el TextView antes de mostrar nuevos datos
            val dbHandler =
                AyudaDB(this, null) // Crea una instancia de AyudaDb para manejar la base de datos
            val cursor =
                dbHandler.getAllName() // Obtiene un cursor con todos los nombres de la base de datos

            if (cursor != null) { // Verifica si el cursor no es nulo
                cursor!!.moveToFirst() // Mueve el cursor a la primera fila (si existe)

                // Agrega el nombre de la primera fila al TextView (tvMuestraNombre)
                // tvMuestraNombre.append((cursor.getString(cursor.getColumnIndex(AyudaDb.COLUMN_NAME))))
                tvMuestraNombre.append((cursor.getString(cursor.getColumnIndex(AyudaDB.COLUMN_NAME))))


                // Itera a través del resto de las filas en el cursor
                while (cursor.moveToNext()) {
                    // Agrega los nombres de las filas siguientes al TextView, separados por nueva línea
                    tvMuestraNombre.append((cursor.getString(cursor.getColumnIndex(AyudaDB.COLUMN_NAME))))
                    tvMuestraNombre.append(System.getProperty("line.separator"))
                }

                cursor.close() // Cierra el cursor después de usarlo
            }


        }
    }
}