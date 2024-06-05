package com.ghostly

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//Adaptador para el RecyclerView del calendario
class CalendarAdapter(
    private val days: List<DayModel>,
    private val onItemClick: (DayModel) -> Unit
) : RecyclerView.Adapter<CalendarAdapter.DayViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.calendar_day_item, parent, false)
        return DayViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val day = days[position]
        holder.bind(day, onItemClick)
    }

    override fun getItemCount(): Int = days.size

    class DayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dayText: TextView = itemView.findViewById(R.id.day_text)
        private val emotionImage: ImageView = itemView.findViewById(R.id.emotion_image)

        fun bind(day: DayModel, onItemClick: (DayModel) -> Unit) {
            if (day.day != 0) {
                dayText.text = day.day.toString()
                emotionImage.setImageResource(day.emotion)

                // Agregar registro de depuración
                Log.d("CalendarAdapter", "Binding day ${day.day} with emotion ${day.emotion}")

                itemView.visibility = View.VISIBLE
                itemView.setOnClickListener { onItemClick(day) }
            } else {
                itemView.visibility = View.INVISIBLE
            }
        }
    }
}
