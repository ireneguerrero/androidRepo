package com.ghostly

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

class SleepTrackerFragment : Fragment() {

    private lateinit var wentToBedText: TextView
    private lateinit var wokeUpText: TextView
    private lateinit var timeAsleepText: TextView
    private var wentToBedTime: Calendar = Calendar.getInstance()
    private var wokeUpTime: Calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sleep_tracker, container, false)

        wentToBedText = view.findViewById(R.id.went_to_bed_time)
        wokeUpText = view.findViewById(R.id.woke_up_time)
        timeAsleepText = view.findViewById(R.id.time_asleep)

        updateTimes()

        wentToBedText.setOnClickListener {
            showTimePicker(wentToBedTime) { time ->
                wentToBedTime = time
                updateTimes()
            }
        }

        wokeUpText.setOnClickListener {
            showTimePicker(wokeUpTime) { time ->
                wokeUpTime = time
                updateTimes()
            }
        }

        val doneButton: Button = view.findViewById(R.id.done_button)
        doneButton.setOnClickListener {
            saveSleepData()
        }

        return view
    }

    private fun updateTimes() {
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        wentToBedText.text = timeFormat.format(wentToBedTime.time)
        wokeUpText.text = timeFormat.format(wokeUpTime.time)
        val diff = wokeUpTime.timeInMillis - wentToBedTime.timeInMillis
        val hours = (diff / (1000 * 60 * 60)).toInt()
        val minutes = ((diff / (1000 * 60)) % 60).toInt()
        timeAsleepText.text = getString(R.string.time_asleep_format, hours, minutes)
    }

    private fun showTimePicker(initialTime: Calendar, onTimeSet: (Calendar) -> Unit) {
        val hour = initialTime.get(Calendar.HOUR_OF_DAY)
        val minute = initialTime.get(Calendar.MINUTE)
        TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
            val newTime = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, selectedHour)
                set(Calendar.MINUTE, selectedMinute)
            }
            onTimeSet(newTime)
        }, hour, minute, true).show()
    }

    private fun saveSleepData() {
        // Aquí puedes guardar los datos en una base de datos, enviar a otra actividad, etc.
        // Por ahora, mostraremos un mensaje de confirmación.
        Toast.makeText(requireContext(), "Sleep data saved successfully!", Toast.LENGTH_SHORT).show()

        // Vuelve a la pantalla anterior
        requireActivity().supportFragmentManager.popBackStack()
    }
}
