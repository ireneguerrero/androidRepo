package com.ficheros2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLeerArchivo: Button = findViewById(R.id.btnLeerArchivo)
        val tvMensaje:TextView=findViewById(R.id.tvMensaje)
        btnLeerArchivo.setOnClickListener {
            leerArchivo(tvMensaje)
        }
    }

    private fun leerArchivo(textView: TextView) {
        try {
            val fileName = "miFichero"

            // Abrir un BufferedReader para leer el archivo
            val br = BufferedReader(InputStreamReader(openFileInput(fileName)))

            // Leer el contenido completo del archivo
            val contenido = StringBuilder()
            var linea: String? = br.readLine()
            while (linea != null) {
                contenido.append(linea).append('\n')
                linea = br.readLine()
            }

            // Cerrar el BufferedReader
            br.close()

            // Mostrar el contenido en el TextView
            textView.text = contenido.toString()

        } catch (e: Exception) {
            // Manejar errores
            textView.text = "Error al leer el archivo: ${e.message}"
        }
    }
}
