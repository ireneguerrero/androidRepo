package com.fragmentos1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, HelloFragment())
                .commit()
        }

    }

    fun displayGoodbyeFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, GoodbyeFragment()).addToBackStack(null).commit()
    }

}