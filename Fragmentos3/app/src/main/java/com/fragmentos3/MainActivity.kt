package com.fragmentos3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Mostrar el diálogo cuando se inicia la actividad
        val dialogFragment = MyAlertDialogFragment()
        dialogFragment.show(supportFragmentManager, "MyAlertDialogFragment")

    }
}