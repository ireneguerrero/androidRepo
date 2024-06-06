package com.ghostly

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import java.text.SimpleDateFormat
import java.util.*

//Gestiona la interfaz de usuario principal para las estadísticas
class StatisticsActivity : AppCompatActivity() {

    private lateinit var statisticsViewPager: ViewPager2
    private lateinit var monthYearText: TextView
    private lateinit var statisticsPagerAdapter: StatisticsPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        monthYearText = findViewById(R.id.month_year_text)
        statisticsViewPager = findViewById(R.id.statistics_view_pager)

        statisticsPagerAdapter = StatisticsPagerAdapter(this)
        statisticsViewPager.adapter = statisticsPagerAdapter

        // Posicionar en el mes actual al iniciar la actividad
        val initialPosition = Int.MAX_VALUE / 2
        statisticsViewPager.setCurrentItem(initialPosition, false)

        // Actualizar el texto del mes y año
        updateMonthYearText(0)

        statisticsViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val monthOffset = position - initialPosition
                updateMonthYearText(monthOffset)
                val fragmentTag = statisticsPagerAdapter.getFragmentTag(position)
                val fragment = supportFragmentManager.findFragmentByTag(fragmentTag) as? StatisticsFragment
                fragment?.updateStatistics()
            }
        })

        setupBottomNavigation()
    }

    private fun updateMonthYearText(monthOffset: Int) {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, monthOffset)
        val monthYearTextFormatted = SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(calendar.time)
        monthYearText.text = monthYearTextFormatted
    }

    private fun setupBottomNavigation() {
        val calendarButton: ImageView = findViewById(R.id.navigation_calendar)
        calendarButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val statisticsButton: ImageView = findViewById(R.id.navigation_statistics)
        statisticsButton.setOnClickListener {
            // Ya estamos en StatisticsActivity
        }

        val calculatorButton: ImageButton = findViewById(R.id.navigation_calculator)
        calculatorButton.setOnClickListener {
            val intent = Intent(this, HappinessCalculatorActivity::class.java)
            startActivity(intent)
        }

        val registerButton: ImageButton = findViewById(R.id.navigation_register)
        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            // Añadir el día actual al intent
            val today = Calendar.getInstance()
            intent.putExtra("SELECTED_DAY", today.get(Calendar.DAY_OF_MONTH))
            intent.putExtra("SELECTED_MONTH", today.get(Calendar.MONTH))
            intent.putExtra("SELECTED_YEAR", today.get(Calendar.YEAR))
            startActivity(intent)
        }
    }
}
