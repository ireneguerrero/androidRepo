package com.ghostly

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var calendarViewPager: ViewPager2
    private lateinit var monthYearText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        monthYearText = findViewById(R.id.month_year_text)
        calendarViewPager = findViewById(R.id.calendar_view_pager)

        val calendarPagerAdapter = CalendarPagerAdapter(this) { day ->
            showRegisterDayDialog(day)
        }
        calendarViewPager.adapter = calendarPagerAdapter

        // Posicionar en el mes actual al iniciar la aplicación
        val initialPosition = Int.MAX_VALUE / 2
        calendarViewPager.setCurrentItem(initialPosition, false)

        // Actualizar el texto del mes y año
        updateMonthYearText(0)

        calendarViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val monthOffset = position - initialPosition
                updateMonthYearText(monthOffset)
            }
        })

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

    private fun showRegisterDayDialog(day: DayModel) {
        val dialog = RegisterDayDialogFragment { emotion ->
            day.emotion = emotion
            val intent = Intent(this, RegisterActivity::class.java).apply {
                putExtra("SELECTED_DAY", day.day)
                putExtra("SELECTED_MONTH", day.month)
                putExtra("SELECTED_YEAR", day.year)
                putExtra("SELECTED_EMOTION", emotion)
            }
            startActivity(intent)
        }
        dialog.show(supportFragmentManager, "RegisterDayDialog")
    }

    private fun updateMonthYearText(monthOffset: Int) {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, monthOffset)
        val monthYearText = SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(calendar.time)
        findViewById<TextView>(R.id.month_year_text).text = monthYearText
    }

}
