package com.ghostly

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class StatisticsActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var moodFlowChart: LineChart
    private lateinit var moodBarChart: BarChart
    private lateinit var averageBedtimeView: TextView
    private lateinit var averageWakeTimeView: TextView
    private lateinit var averageSleepDurationView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        db = FirebaseFirestore.getInstance()
        moodFlowChart = findViewById(R.id.mood_flow_chart)
        moodBarChart = findViewById(R.id.mood_bar_chart)
        averageBedtimeView = findViewById(R.id.average_bedtime)
        averageWakeTimeView = findViewById(R.id.average_wake_time)
        averageSleepDurationView = findViewById(R.id.average_sleep_duration)

        loadMoodData()
        loadSleepData()
    }

    private fun loadMoodData() {
        db.collection("Registro")
            .get()
            .addOnSuccessListener { documents ->
                val moodEntries = ArrayList<BarEntry>()
                val moodCount = HashMap<Int, Int>()

                for (document in documents) {
                    val dateStr = document.getString("fecha") ?: continue
                    val mood = document.getLong("numeroDia")?.toInt() ?: continue

                    val date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(dateStr)
                    val dayOfMonth = SimpleDateFormat("d", Locale.getDefault()).format(date).toFloat()

                    moodEntries.add(BarEntry(dayOfMonth, mood.toFloat()))

                    moodCount[mood] = moodCount.getOrDefault(mood, 0) + 1
                }

                // Actualizar el gráfico de flujo de humor
                val lineDataSet = LineDataSet(moodEntries as List<Entry>?, "Mood Flow")
                val lineData = LineData(lineDataSet)
                moodFlowChart.data = lineData
                moodFlowChart.invalidate()

                // Actualizar el gráfico de barras de humor
                val barEntries = moodCount.map { BarEntry(it.key.toFloat(), it.value.toFloat()) }
                val barDataSet = BarDataSet(barEntries, "Mood Bar")
                val barData = BarData(barDataSet)
                moodBarChart.data = barData
                moodBarChart.invalidate()
            }
    }

    private fun loadSleepData() {
        db.collection("Registro")
            .get()
            .addOnSuccessListener { documents ->
                var totalBedtimeMinutes = 0
                var totalWakeTimeMinutes = 0
                var totalSleepMinutes = 0
                var count = 0

                for (document in documents) {
                    val bedTimeStr = document.getString("bedTime") ?: continue
                    val wakeTimeStr = document.getString("wakeTime") ?: continue

                    val bedTime = SimpleDateFormat("HH:mm", Locale.getDefault()).parse(bedTimeStr)
                    val wakeTime = SimpleDateFormat("HH:mm", Locale.getDefault()).parse(wakeTimeStr)

                    val bedTimeCalendar = Calendar.getInstance().apply { time = bedTime }
                    val wakeTimeCalendar = Calendar.getInstance().apply { time = wakeTime }

                    val bedTimeMinutes = bedTimeCalendar.get(Calendar.HOUR_OF_DAY) * 60 + bedTimeCalendar.get(Calendar.MINUTE)
                    val wakeTimeMinutes = wakeTimeCalendar.get(Calendar.HOUR_OF_DAY) * 60 + wakeTimeCalendar.get(Calendar.MINUTE)

                    val sleepDuration = if (wakeTimeMinutes > bedTimeMinutes) {
                        wakeTimeMinutes - bedTimeMinutes
                    } else {
                        (24 * 60 - bedTimeMinutes) + wakeTimeMinutes
                    }

                    totalBedtimeMinutes += bedTimeMinutes
                    totalWakeTimeMinutes += wakeTimeMinutes
                    totalSleepMinutes += sleepDuration
                    count++
                }

                if (count > 0) {
                    val avgBedtime = totalBedtimeMinutes / count
                    val avgWakeTime = totalWakeTimeMinutes / count
                    val avgSleepDuration = totalSleepMinutes / count

                    averageBedtimeView.text = formatMinutesToTime(avgBedtime)
                    averageWakeTimeView.text = formatMinutesToTime(avgWakeTime)
                    averageSleepDurationView.text = formatMinutesToDuration(avgSleepDuration)
                }
            }
    }

    private fun formatMinutesToTime(minutes: Int): String {
        val hours = minutes / 60
        val mins = minutes % 60
        return String.format(Locale.getDefault(), "AM %02d:%02d", hours, mins)
    }

    private fun formatMinutesToDuration(minutes: Int): String {
        val hours = minutes / 60
        val mins = minutes % 60
        return String.format(Locale.getDefault(), "h %02d:%02d", hours, mins)
    }
}
