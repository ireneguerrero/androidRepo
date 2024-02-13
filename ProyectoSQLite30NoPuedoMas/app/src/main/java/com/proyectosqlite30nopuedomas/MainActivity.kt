package com.proyectosqlite30nopuedomas

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this
        val dbHelper = AlumnosDatabaseHelper(context)

        // Agregar un alumno de ejemplo
        val id = dbHelper.addAlumno("Juan", "Perez", "12345678A", 25, "1A")
        println("Alumno agregado con ID: $id")

        // Obtener y mostrar todos los alumnos en el TextView
        val alumnosTextView: TextView = findViewById(R.id.alumnosTextView)
        val alumnos = dbHelper.getAllAlumnos()
        val alumnosString = StringBuilder()
        for (alumno in alumnos) {
            alumnosString.append("${alumno.nombre} ${alumno.apellidos}, DNI: ${alumno.dni}, Edad: ${alumno.edad}, Curso: ${alumno.curso}\n")
        }
        alumnosTextView.text = alumnosString.toString()
    }
}
