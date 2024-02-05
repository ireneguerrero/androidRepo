package com.recyclerview2

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
            Person("Alberto ", "Ruiz, ", "Calle Cenec, ","Málaga "),
            Person("Juan ", "Zamora, ","Calle sí, ","Málaga "),
            Person("Eva ","García, ","Calle no, ","Málaga"),
            Person("Alberto ","Rodríguez, ", "Calle no puedo más, ","Málaga"),
            Person("Laura ","González, ","Avenida muerte, ","Málaga"),
            Person("Cristina ","Fernández, ","Avenida callao, ","Málaga"),
            Person("Isabel ","López, ","Avenida a, ","Málaga"),
            Person("Pedro ","Martínez, ","Calle b, ","Málaga"),
            Person("Jose ","Sánchez, ","Avenida c, ","Málaga"),
            Person("Manuel ","Pérez, ","Calle d, ","Málaga"),
            Person("Diana ","Díaz, ","Avenida e, ","Málaga"),
        )
    }
}
