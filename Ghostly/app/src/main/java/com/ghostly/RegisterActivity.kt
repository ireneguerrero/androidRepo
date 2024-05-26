package com.ghostly

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.customview.SleepTimePickerView
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val selectedDay = intent.getIntExtra("SELECTED_DAY", -1)
        val selectedEmotion = intent.getIntExtra("SELECTED_EMOTION", -1)

        // Muestra el día seleccionado o el día actual si no hay un día seleccionado
        val dateText: TextView = findViewById(R.id.date_text)
        if (selectedDay != -1) {
            dateText.text = "Selected Day: $selectedDay"
        } else {
            val currentDate = SimpleDateFormat("EEEE, MMMM d", Locale.getDefault()).format(Date())
            dateText.text = currentDate
        }

        // Configura los botones de emociones para mostrar el seleccionado
        val happyButton: ImageButton = findViewById(R.id.emotion_happy)
        val smileButton: ImageButton = findViewById(R.id.emotion_smile)
        val neutralButton: ImageButton = findViewById(R.id.emotion_neutral)
        val sadButton: ImageButton = findViewById(R.id.emotion_sad)
        val verysadButton: ImageButton = findViewById(R.id.emotion_verysad)

        val buttons = listOf(happyButton, smileButton, neutralButton, sadButton, verysadButton)

        // Marca el botón correspondiente a la emoción seleccionada
        buttons.forEach { button ->
            if (button.id == getEmotionButtonId(selectedEmotion)) {
                button.isSelected = true
            }
        }

        // Configura la selección de los botones
        buttons.forEach { button ->
            button.setOnClickListener {
                buttons.forEach { it.isSelected = false }
                button.isSelected = true
            }
        }

        // Configurar el campo de entrada de sueño para mostrar el diálogo del selector de tiempo
        val sleepInput: EditText = findViewById(R.id.sleep_input)
        sleepInput.setOnClickListener {
            showSleepTimePickerDialog()
        }
    }

    private fun showSleepTimePickerDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_sleep_picker, null)
        val sleepTimePickerView: SleepTimePickerView = dialogView.findViewById(R.id.sleep_time_picker)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        dialogView.findViewById<ImageButton>(R.id.close_button).setOnClickListener {
            dialog.dismiss()
        }

        dialogView.findViewById<Button>(R.id.button_done).setOnClickListener {
            // Aquí puedes obtener los tiempos seleccionados del SleepTimePickerView
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun getEmotionButtonId(emotion: Int): Int {
        return when (emotion) {
            R.drawable.muyfeliz -> R.id.emotion_happy
            R.drawable.feliz -> R.id.emotion_smile
            R.drawable.normal -> R.id.emotion_neutral
            R.drawable.triste -> R.id.emotion_sad
            R.drawable.muytriste -> R.id.emotion_verysad
            else -> -1
        }
    }
}
