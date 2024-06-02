package com.ghostly

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CalendarFragment : Fragment() {

    private lateinit var calendarRecyclerView: RecyclerView
    private lateinit var dayList: List<DayModel>
    private lateinit var onDayClickListener: (DayModel) -> Unit

    companion object {
        fun newInstance(dayList: List<DayModel>, listener: (DayModel) -> Unit): CalendarFragment {
            val fragment = CalendarFragment()
            fragment.setOnDayClickListener(listener)
            val args = Bundle()
            args.putParcelableArrayList("DAY_LIST", ArrayList(dayList))
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let {
            dayList = it.getParcelableArrayList("DAY_LIST") ?: emptyList()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        calendarRecyclerView = view.findViewById(R.id.calendar_recycler_view)
        calendarRecyclerView.layoutManager = GridLayoutManager(context, 7)
        calendarRecyclerView.adapter = CalendarAdapter(dayList, onDayClickListener)
        return view
    }

    fun setOnDayClickListener(listener: (DayModel) -> Unit) {
        onDayClickListener = listener
    }
}
