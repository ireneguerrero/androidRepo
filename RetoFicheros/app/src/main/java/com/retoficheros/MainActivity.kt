package com.retoficheros

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.editText)
        val btnGuardar: Button = findViewById(R.id.btnGuardar)
        val btnLeer: Button = findViewById(R.id.btnLeer)
        val textView: TextView = findViewById(R.id.textView)

        btnGuardar.setOnClickListener {
            val texto = editText.text.toString()
            guardarArchivo("miArchivo", texto)
        }

        btnLeer.setOnClickListener {
            val contenido = leerArchivo("miArchivo")
            textView.text = contenido
        }
    }

    private fun guardarArchivo(nombreArchivo: String, contenido: String) {
        try {
            val osw = OutputStreamWriter(openFileOutput(nombreArchivo, MODE_PRIVATE))
            osw.write(contenido)
            osw.flush()
            osw.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun leerArchivo(nombreArchivo: String): String {
        var contenido = ""
        try {
            val br = BufferedReader(InputStreamReader(openFileInput(nombreArchivo)))
            contenido = br.readLine()
            br.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return contenido
    }
}
