package com.ghostly

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

//Maneja los fragmentos en el ViewPager2 dentro de StatisticsActivity
class StatisticsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun createFragment(position: Int): Fragment {
        val monthOffset = position - (Int.MAX_VALUE / 2)
        return StatisticsFragment.newInstance(monthOffset)
    }

    fun getFragmentTag(position: Int): String {
        return "f$position"
    }
}
