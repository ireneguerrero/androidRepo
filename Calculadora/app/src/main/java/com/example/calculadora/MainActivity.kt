package com.example.calculadora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun performOperation(view: View) {
        val operador1 = findViewById<EditText>(R.id.editTextOperand1).text.toString()
        val operador2 = findViewById<EditText>(R.id.editTextOperand2).text.toString()

        if (operador1.isNotEmpty() && operador2.isNotEmpty()) {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("Operador 1", operador1.toDouble())
            intent.putExtra("Operador 2", operador2.toDouble())

            when (view.id) {
                R.id.btnSuma -> intent.putExtra("Operacion", "+")
                R.id.btnResta -> intent.putExtra("Operacion", "-")
                R.id.btnMultiplicar -> intent.putExtra("Operacion", "*")
                R.id.btnDividir -> intent.putExtra("Operacion", "/")
            }

            startActivity(intent)
        }
    }
}