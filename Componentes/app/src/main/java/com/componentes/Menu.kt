package com.componentes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val boton1 = findViewById<Button>(R.id.btn1)
        boton1.setOnClickListener {
            navegarEjercicio1()
        }
    }

    private fun navegarEjercicio1() {
        val intent = Intent(this, CardView::class.java)
        startActivity(intent)
    }
}