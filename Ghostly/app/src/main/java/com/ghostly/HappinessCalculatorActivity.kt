package com.ghostly

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

//Calculadora de la felicidad
class HappinessCalculatorActivity : AppCompatActivity() {

    private lateinit var daysVeryHappy: EditText
    private lateinit var daysHappy: EditText
    private lateinit var daysNeutral: EditText
    private lateinit var daysSad: EditText
    private lateinit var daysVerySad: EditText
    private lateinit var calculateButton: Button
    private lateinit var resultText: TextView
    private lateinit var errorText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_happiness_calculator)

        daysVeryHappy = findViewById(R.id.days_very_happy)
        daysHappy = findViewById(R.id.days_happy)
        daysNeutral = findViewById(R.id.days_neutral)
        daysSad = findViewById(R.id.days_sad)
        daysVerySad = findViewById(R.id.days_very_sad)
        calculateButton = findViewById(R.id.calculate_button)
        resultText = findViewById(R.id.result_text)
        errorText = findViewById(R.id.error_text)

        val inputFilter = InputFilter { source, start, end, dest, dstart, dend ->
            try {
                val input = (dest.subSequence(0, dstart).toString() + source + dest.subSequence(dend, dest.length)).toInt()
                if (input in 0..31) null else ""
            } catch (e: NumberFormatException) {
                ""
            }
        }

        daysVeryHappy.filters = arrayOf(inputFilter)
        daysHappy.filters = arrayOf(inputFilter)
        daysNeutral.filters = arrayOf(inputFilter)
        daysSad.filters = arrayOf(inputFilter)
        daysVerySad.filters = arrayOf(inputFilter)

        calculateButton.setOnClickListener {
            calculateHappiness()
        }

        setupBottomNavigation()
    }

    private fun calculateHappiness() {
        val veryHappyDays = daysVeryHappy.text.toString().toIntOrNull()
        val happyDays = daysHappy.text.toString().toIntOrNull()
        val neutralDays = daysNeutral.text.toString().toIntOrNull()
        val sadDays = daysSad.text.toString().toIntOrNull()
        val verySadDays = daysVerySad.text.toString().toIntOrNull()

        if (veryHappyDays == null || happyDays == null || neutralDays == null || sadDays == null || verySadDays == null) {
            errorText.text = "Por favor, rellena todos los campos."
            resultText.text = ""
            return
        }

        val totalDays = veryHappyDays + happyDays + neutralDays + sadDays + verySadDays

        if (totalDays !in 0..31) {
            errorText.text = "La suma de todos los días debe ser entre 0 y 31."
            resultText.text = ""
            return
        }

        errorText.text = ""

        if (totalDays == 0) {
            resultText.text = "Por favor, ingrese los días para calcular."
            return
        }

        val happinessPercent = ((veryHappyDays + happyDays * 0.75 + neutralDays * 0.5 + sadDays * 0.25) / totalDays * 100).toInt()

        val happinessLevel = when {
            happinessPercent >= 80 -> "Muy Feliz"
            happinessPercent >= 60 -> "Feliz"
            happinessPercent >= 40 -> "Neutral"
            happinessPercent >= 20 -> "Triste"
            else -> "Muy Triste"
        }

        resultText.text = "Tu nivel de felicidad es: $happinessLevel ($happinessPercent%)"
    }

    private fun setupBottomNavigation() {
        val calendarButton: ImageView = findViewById(R.id.navigation_calendar)
        calendarButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val statisticsButton: ImageView = findViewById(R.id.navigation_statistics)
        statisticsButton.setOnClickListener {
            val intent = Intent(this, StatisticsActivity::class.java)
            startActivity(intent)
        }

        val storeButton: ImageButton = findViewById(R.id.navigation_calculator)
        storeButton.setOnClickListener {
            // Already in HappinessCalculatorActivity, no action needed
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
