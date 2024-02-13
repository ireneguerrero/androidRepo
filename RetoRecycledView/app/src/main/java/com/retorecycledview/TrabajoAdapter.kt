package com.retorecycledview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TrabajoAdapter (private val trabajos: List<Trabajo>, private val onCourseClickListener: (Trabajo) -> Unit) :
    RecyclerView.Adapter<TrabajoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trabajo = trabajos[position]

        // Aquí es donde debes obtener el TextView dentro de la vista item_course.xml
        val trabajoTextView: TextView = holder.itemView.findViewById(R.id.trabajosNameTextView)

        // Ahora, establece el texto en el TextView
        trabajoTextView.text = trabajo.nombre

        // Asigna el clic al itemView (puede seguir usándolo si es necesario)
        holder.itemView.setOnClickListener { onCourseClickListener(trabajo) }
    }

    override fun getItemCount(): Int = trabajos.size
}