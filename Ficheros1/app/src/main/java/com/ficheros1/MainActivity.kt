package com.ficheros1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCrearArchivo: Button =findViewById(R.id.btnCrearArchivo)

        btnCrearArchivo.setOnClickListener{
            crearArchivo()
        }
    }

    private fun crearArchivo(){
        try {
            val fileName="Gaspar chupa"
            val fileContent="Irene se quiere morir"

            //Abrir un OutputStreamWriter en el modo privado (solo accesible por esta aplicación)
            var osw:OutputStreamWriter= OutputStreamWriter(openFileOutput(fileName, Context.MODE_PRIVATE))

            //Escribir el contenido en el archivo
            osw.write(fileContent)

            //Limpiar y cerrar el OutputStreamWriter
            osw.flush()
            osw.close()

            Log.d("Éxito","Archivo creado con éxito")
        }catch(e:Exception){
            Log.e("Error","Error al usar OutputStreamWriter: "+e.message)
        }
    }

}