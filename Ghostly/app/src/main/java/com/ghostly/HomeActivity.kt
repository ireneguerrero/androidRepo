package com.ghostly

import com.ghostly.R
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.menu)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets



        }
        val email = intent.extras?.getString("email").orEmpty()
        var tvemail = findViewById<TextView>(R.id.tvEmail)
        tvemail.text=email

        val btnSalir = findViewById<Button>(R.id.btnSalir)
        val btnPrueba = findViewById<Button>(R.id.btnPrueba)
        val btnSolucionarBD = findViewById<Button>(R.id.btnBDSOLUCION)


        val prefs = getSharedPreferences(getString(R.string.prefs_file),Context.MODE_PRIVATE).edit()
        prefs.putString("email",email)
        prefs.apply()


        btnSalir.setOnClickListener {
            val prefs2 = getSharedPreferences(getString(R.string.prefs_file),Context.MODE_PRIVATE).edit()
            prefs2.clear()
            prefs2.apply()

            FirebaseAuth.getInstance().signOut()
            finish()
        }


    }
}