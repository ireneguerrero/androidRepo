package com.ghostly

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.*

class CalendarPagerAdapter(
    activity: AppCompatActivity,
    private val onDayClickListener: (DayModel) -> Unit
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun createFragment(position: Int): Fragment {
        val monthOffset = position - (Int.MAX_VALUE / 2)
        val dayList = initializeDayList(monthOffset)
        return CalendarFragment.newInstance(dayList) { day ->
            onDayClickListener(day)
        }
    }

    private fun initializeDayList(monthOffset: Int): List<DayModel> {
        val days = mutableListOf<DayModel>()

        // Get the calendar instance and adjust the month according to the offset
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, monthOffset)
        calendar.set(Calendar.DAY_OF_MONTH, 1)

        // Get the first day of the week for the current month
        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        // Determine the number of days in the current month
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        // Add empty days at the start if the month doesn't start on Monday
        for (i in 1 until (firstDayOfWeek - Calendar.MONDAY + 7) % 7) {
            days.add(DayModel(0, 0, 0, 0)) // Empty day
        }

        // Add the days of the current month
        for (day in 1..daysInMonth) {
            days.add(DayModel(day, R.drawable.nada, calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR)))
        }

        return days
    }
}
