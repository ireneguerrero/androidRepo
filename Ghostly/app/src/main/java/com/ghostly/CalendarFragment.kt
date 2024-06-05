package com.ghostly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

//Muestra un calendario con los días registrados y permite la selección de días
class CalendarFragment : Fragment() {

    private lateinit var dayList: MutableList<DayModel>
    private lateinit var onDayClickListener: (DayModel) -> Unit
    private lateinit var db: FirebaseFirestore
    private lateinit var adapter: CalendarAdapter
    private var listenerRegistration: ListenerRegistration? = null

    companion object {
        private const val ARG_DAY_LIST = "day_list"
        private const val ARG_ON_DAY_CLICK_LISTENER = "on_day_click_listener"

        fun newInstance(dayList: List<DayModel>, onDayClickListener: (DayModel) -> Unit): CalendarFragment {
            val fragment = CalendarFragment()
            fragment.dayList = dayList.toMutableList()
            fragment.onDayClickListener = onDayClickListener
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        db = FirebaseFirestore.getInstance()

        val recyclerView: RecyclerView = view.findViewById(R.id.calendar_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(context, 7)
        adapter = CalendarAdapter(dayList, onDayClickListener)
        recyclerView.adapter = adapter

        loadEmotionsForDays()

        return view
    }

    fun loadEmotionsForDays() {
        listenerRegistration?.remove() // Remove any existing listeners
        listenerRegistration = db.collection("Registro")
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    // Handle error
                    return@addSnapshotListener
                }

                for (document in snapshots!!) {
                    val dateString = document.getString("fecha") ?: continue
                    val dateParts = dateString.split("/")
                    if (dateParts.size != 3) continue
                    val day = dateParts[0].toInt()
                    val month = dateParts[1].toInt() - 1
                    val year = dateParts[2].toInt()
                    val emotionNumber = document.getLong("numeroDia")?.toInt() ?: 0

                    val dayModel = dayList.find { it.day == day && it.month == month && it.year == year }
                    dayModel?.let {
                        it.emotion = getDrawableForEmotion(emotionNumber)
                        val index = dayList.indexOf(it)
                        adapter.notifyItemChanged(index) // Notify adapter to update the specific item
                    }
                }
            }
    }

    private fun getDrawableForEmotion(emotionNumber: Int): Int {
        return when (emotionNumber) {
            0 -> R.drawable.muyfeliz
            1 -> R.drawable.feliz
            2 -> R.drawable.normal
            3 -> R.drawable.triste
            4 -> R.drawable.muytriste
            else -> R.drawable.nada
        }
    }

    override fun onDestroyView() {
        listenerRegistration?.remove() // Clean up the listener
        super.onDestroyView()
    }
}
