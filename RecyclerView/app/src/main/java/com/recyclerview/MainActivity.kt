package com.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val personList = generatePersonList()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val itemPersona = ItemPersona(personList)
        recyclerView.adapter = itemPersona
    }

    private fun generatePersonList(): List<Person> {
        return listOf(
            Person("Alberto"),
            Person("Juan"),
            Person("Eva"),
            Person("Alberto"),
            Person("Laura"),
            Person("Cristina"),
            Person("Isabel"),
            Person("Pedro"),
            Person("Jose"),
            Person("Manuel"),
            Person("Diana"),
            )
    }
}
