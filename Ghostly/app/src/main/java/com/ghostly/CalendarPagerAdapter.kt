package com.ghostly

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class CalendarPagerAdapter(
    activity: AppCompatActivity,
    private val onDayClickListener: (DayModel) -> Unit,
    private val dayList: List<DayModel>
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun createFragment(position: Int): Fragment {
        val monthOffset = position - (Int.MAX_VALUE / 2)
        val fragment = CalendarFragment.newInstance(dayList) { day ->
            onDayClickListener(day)
        }
        return fragment
    }
}
