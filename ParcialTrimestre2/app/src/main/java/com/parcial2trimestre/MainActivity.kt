package com.parcial2trimestre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    private var opcionSeleccionada: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnSiguiente = findViewById<Button>(R.id.btnEntrar)
        val etNombre = findViewById<EditText>(R.id.nombre)
        val etApellidos = findViewById<EditText>(R.id.apellidos)
        val etFecha = findViewById<EditText>(R.id.fecha)
        val etContrasenia = findViewById<EditText>(R.id.contrasenia)
        val etConfirmContrasenia = findViewById<EditText>(R.id.confirmcontrasenia)

        val cita = findViewById<CardView>(R.id.cv)

        cita.setOnClickListener {
            handleCardViewClick(cita)
        }

        btnSiguiente.setOnClickListener {
            val nombre = etNombre.text.toString()
            val apellidos = etApellidos.text.toString()
            val fecha = etFecha.text.toString()
            val contra = etContrasenia.text.toString()
            val confirmcontra = etConfirmContrasenia.text.toString()
            if (nombre.isNotEmpty() && apellidos.isNotEmpty() && fecha.isNotEmpty() && contra.isNotEmpty() && confirmcontra.isNotEmpty()) {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("EXTRA_NOMBRE", nombre)
                intent.putExtra("EXTRA_APELLIDOS", apellidos)
                intent.putExtra("EXTRA_FECHA", fecha)
                intent.putExtra("EXTRA_CONTRA", contra)
                intent.putExtra("EXTRA_CONFCONTRA", confirmcontra)
                intent.putExtra("EXTRA_CITA", opcionSeleccionada)
                startActivity(intent)
            }
        }
    }

    private fun handleCardViewClick(cardView: CardView) {
        when (cardView.id) {
            R.id.cv -> {
                println("Se ha pulsado CardView1")
                Log.d("CardView", "Has pulsado CardView1")
                opcionSeleccionada = "La cita es urgente"
            }
        }
    }

}