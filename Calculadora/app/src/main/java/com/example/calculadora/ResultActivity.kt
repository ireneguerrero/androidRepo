package com.example.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val operador1 = intent.getDoubleExtra("Operador 1", 0.0)
        val operador2 = intent.getDoubleExtra("Operador 2", 0.0)
        val operacion = intent.getStringExtra("Operacion")

        val result = performOperation(operador1, operador2, operacion)
        findViewById<TextView>(R.id.textViewResult).text = "Resultado: $result"
    }

    private fun performOperation(operador1: Double, operador2: Double, operacion: String?): Double {
        return when (operacion) {
            "+" -> operador1 + operador2
            "-" -> operador1 - operador2
            "*" -> operador1 * operador2
            "/" -> if (operador2 != 0.0) operador1 / operador2 else Double.NaN
            else -> 0.0
        }
    }
}