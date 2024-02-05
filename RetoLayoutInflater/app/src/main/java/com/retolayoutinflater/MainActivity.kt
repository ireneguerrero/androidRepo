package com.retolayoutinflater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.marginLeft

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflar el diseño XML
        val rootView: View = LayoutInflater.from(this).inflate(R.layout.activity_main, null)

        // Obtener referencia al linearLayout (puedes usar el tipo de layout que estés utilizando)
        val linearLayout = rootView.findViewById<CardView>(R.id.cardView2)

        // Crear un nuevo TextView dinámicamente
        val nuevoTextView = TextView(this)
        nuevoTextView.text = "Hola, Irene"


        // Agregar el nuevo TextView al contenedor
        linearLayout.addView(nuevoTextView)

        // Establecer la vista inflada en la actividad
        setContentView(rootView)
    }
}