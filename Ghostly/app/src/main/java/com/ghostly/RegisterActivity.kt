package com.ghostly

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val selectedDay = intent.getIntExtra("SELECTED_DAY", -1)
        val selectedEmotion = intent.getIntExtra("SELECTED_EMOTION", -1)

        // Muestra el día seleccionado y la emoción seleccionada en la pantalla de registro
        val dateText: TextView = findViewById(R.id.date_text)
        dateText.text = "Selected Day: $selectedDay"

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
