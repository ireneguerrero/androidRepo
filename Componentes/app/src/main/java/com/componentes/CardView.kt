package com.componentes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.slider.RangeSlider
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CardView : AppCompatActivity() {
    private lateinit var tvIngresos: TextView
    private val incrementoDeIngresos = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_view)

        val rangeSlider = findViewById<RangeSlider>(R.id.rsEdad)
        val tvAnios = findViewById<TextView>(R.id.tvAnios)

        rangeSlider.addOnChangeListener { slider, value, fromUser ->
            // Actualiza el texto del TextView con el valor seleccionado
            tvAnios.text = "${value.toInt()} años"
        }

        val btnMayor = findViewById<FloatingActionButton>(R.id.btnMayor)
        val btnMenor = findViewById<FloatingActionButton>(R.id.btnMenor)
        tvIngresos = findViewById(R.id.tvIngresos)

        btnMayor.setOnClickListener {
            aumentarIngresos()
        }

        btnMenor.setOnClickListener {
            disminuirIngresos()
        }

    }

    private fun aumentarIngresos() {
        val valorActual = tvIngresos.text.toString().toInt()
        val nuevoValor = valorActual + incrementoDeIngresos

        //Asegura que el nuevo valor no exceda el límite superior
        if (nuevoValor <= Int.MAX_VALUE) {
            tvIngresos.text = nuevoValor.toString()
        }
    }

    private fun disminuirIngresos() {
        val valorActual = tvIngresos.text.toString().toInt()
        val nuevoValor = valorActual - incrementoDeIngresos

        //Asegura que el nuevo valor no sea inferior a cero
        if (nuevoValor >= 0) {
            tvIngresos.text = nuevoValor.toString()
        }
    }

}