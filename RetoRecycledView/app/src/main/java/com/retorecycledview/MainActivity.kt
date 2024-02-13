package com.retorecycledview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AlertDialog

// lista de datos
val trabajos = listOf(
    Trabajo("Programación", listOf(Subject("Programador en Android"), Subject("Gestor de BBDD"), Subject("Programador en Java"))),
    Trabajo("Diseño Web", listOf(Subject("Especialista en WordPress"), Subject("Técnico en JavaScript"), Subject("Especialista en PHP"))),
    Trabajo("Marketing Digital", listOf(Subject("Especialista en Redes Sociales"), Subject("Experto en SEO y SEM")))
)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //definicion de recyclerview
        val recyclerViewTrabajos: RecyclerView = findViewById(R.id.recyclerViewTrabajos)
        recyclerViewTrabajos.layoutManager = LinearLayoutManager(this)
        recyclerViewTrabajos.adapter = TrabajoAdapter(trabajos) { trabajo ->
            showSubjectsDialog(trabajo)
        }
    }

    //mostrar alert
    private fun showSubjectsDialog(trabajo: Trabajo) {
        val trabajos = trabajo.trabajos.map { it.nombre }.toTypedArray()

        AlertDialog.Builder(this)
            .setTitle("Asignaturas de ${trabajo.nombre}")
            .setItems(trabajos) { _, _ ->
                // Acción al hacer clic en una asignatura (puedes implementar algo aquí)
            }
            .setPositiveButton("Aceptar") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}