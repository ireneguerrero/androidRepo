package com.ghostly

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

enum class ProviderType{
    BASIC,
    GOOGLE
}

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //Setup
        val bundle=intent.extras
        val nombre=bundle?.getString("nombre")
        val provider=bundle?.getString("provider")
        setup(nombre?:"",provider?:"")

        //Guardado de datos
        val prefs=getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("nombre", nombre)
        prefs.putString("provider",provider)
        prefs.apply()

    }

    private fun setup(nombre:String,provider:String){

    }

}