package com.parcial2trimestre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val tvResult=findViewById<TextView>(R.id.tvMensaje)
        val nombre:String=intent.extras?.getString("EXTRA_NOMBRE").orEmpty()
        val apellidos:String=intent.extras?.getString("EXTRA_APELLIDOS").orEmpty()
        val fecha:String=intent.extras?.getString("EXTRA_FECHA").orEmpty()
        val contra:String=intent.extras?.getString("EXTRA_CONTRA").orEmpty()
        val confirmContra:String=intent.extras?.getString("EXTRA_CONFCONTRA").orEmpty()

        val cita:String=intent.extras?.getString("EXTRA_CITA").orEmpty()
        tvResult.text="\nCita médica: \nNombre: $nombre\nApellidos: $apellidos\nFecha: $fecha\n$cita"
    }
}