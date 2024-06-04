package com.ghostly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CalendarFragment : Fragment() {

    private lateinit var dayList: List<DayModel>
    private lateinit var onDayClickListener: (DayModel) -> Unit

    companion object {
        private const val ARG_DAY_LIST = "day_list"
        private const val ARG_ON_DAY_CLICK_LISTENER = "on_day_click_listener"

        fun newInstance(dayList: List<DayModel>, onDayClickListener: (DayModel) -> Unit): CalendarFragment {
            val fragment = CalendarFragment()
            fragment.dayList = dayList
            fragment.onDayClickListener = onDayClickListener
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.calendar_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(context, 7)
        recyclerView.adapter = CalendarAdapter(dayList, onDayClickListener)
        return view
    }
}
