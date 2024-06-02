package com.ghostly

import android.app.Activity
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var selectedWeather: MutableSet<Int>
    private lateinit var selectedMeals: MutableSet<Int>
    private lateinit var selectedHealth: MutableSet<Int>
    private lateinit var selectedEmotions: MutableSet<Int>
    private var selectedDayEmotion: Int? = null

    private lateinit var bedTimeTextView: TextView
    private lateinit var wakeTimeTextView: TextView
    private lateinit var timeAsleepTextView: TextView
    private var bedTimeHour: Int = 2
    private var bedTimeMinute: Int = 20
    private var wakeTimeHour: Int = 8
    private var wakeTimeMinute: Int = 30

    private lateinit var addPhotoButton: ImageButton
    private lateinit var photoPreview: ImageView

    private lateinit var dateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        selectedWeather = mutableSetOf()
        selectedMeals = mutableSetOf()
        selectedHealth = mutableSetOf()
        selectedEmotions = mutableSetOf()

        // Inicializar vistas de foto
        addPhotoButton = findViewById(R.id.add_photo_button)
        photoPreview = findViewById(R.id.photo_preview)
        dateTextView = findViewById(R.id.date_text)

        addPhotoButton.setOnClickListener {
            openGallery()
        }

        photoPreview.setOnClickListener {
            openGallery()
        }

        val sleepInput = findViewById<EditText>(R.id.sleep_input)
        sleepInput.setOnClickListener {
            showSleepTimePickerDialog()
        }

        val doneButton = findViewById<Button>(R.id.done_button)
        doneButton.setOnClickListener {
            // Lógica para guardar los datos y volver al calendario
            finish() // O la lógica que necesites para volver al calendario
        }

        val dayEmotionButtons = listOf(
            findViewById<ImageButton>(R.id.emotion_happy),
            findViewById<ImageButton>(R.id.emotion_smile),
            findViewById<ImageButton>(R.id.emotion_neutral),
            findViewById<ImageButton>(R.id.emotion_sad),
            findViewById<ImageButton>(R.id.emotion_verysad)
        )

        val weatherButtons = listOf(
            findViewById<ImageButton>(R.id.weather_sunny),
            findViewById<ImageButton>(R.id.weather_cloudy),
            findViewById<ImageButton>(R.id.weather_rainy),
            findViewById<ImageButton>(R.id.weather_windy),
            findViewById<ImageButton>(R.id.weather_snowy)
        )

        val emotionButtons = listOf(
            findViewById<ImageButton>(R.id.emotion_inlove),
            findViewById<ImageButton>(R.id.emotion_celebrating),
            findViewById<ImageButton>(R.id.emotion_relax),
            findViewById<ImageButton>(R.id.emotion_proud),
            findViewById<ImageButton>(R.id.emotion_tired),
            findViewById<ImageButton>(R.id.emotion_anxious),
            findViewById<ImageButton>(R.id.emotion_crying),
            findViewById<ImageButton>(R.id.emotion_mad)
        )

        // Configuración de selección única para "How was your day?"
        setupSingleSelection(dayEmotionButtons)

        // Configuración de selección múltiple para otras secciones
        setupMultiSelection(weatherButtons, selectedWeather)
        setupMultiSelection(emotionButtons, selectedEmotions)

        // Repite la configuración para otras secciones como Meals y Health

        // Obtener la fecha seleccionada del intent
        val selectedDay = intent.getIntExtra("SELECTED_DAY", -1)
        val selectedMonth = intent.getIntExtra("SELECTED_MONTH", -1)

        if (selectedDay != -1 && selectedMonth != -1) {
            val selectedDate = Calendar.getInstance()
            selectedDate.set(Calendar.DAY_OF_MONTH, selectedDay)
            selectedDate.set(Calendar.MONTH, selectedMonth)
            dateTextView.text = formatDate(selectedDate.time)
        } else {
            // Mostrar la fecha actual si no hay una fecha seleccionada
            val currentDate = Date()
            dateTextView.text = formatDate(currentDate)
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            val selectedImageUri: Uri? = data?.data
            if (selectedImageUri != null) {
                try {
                    val inputStream: InputStream? = contentResolver.openInputStream(selectedImageUri)
                    val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
                    photoPreview.setImageBitmap(bitmap)
                    photoPreview.visibility = ImageView.VISIBLE
                    addPhotoButton.visibility = ImageView.GONE
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun showSleepTimePickerDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_sleep_time_picker, null)
        val bedTime = dialogView.findViewById<TextView>(R.id.bed_time)
        val wakeTime = dialogView.findViewById<TextView>(R.id.wake_time)
        val timeAsleep = dialogView.findViewById<TextView>(R.id.time_asleep)
        val timePicker = dialogView.findViewById<TimePicker>(R.id.time_picker)

        bedTime.text = String.format("%02d:%02d", bedTimeHour, bedTimeMinute)
        wakeTime.text = String.format("%02d:%02d", wakeTimeHour, wakeTimeMinute)
        updateSleepDuration(bedTimeHour, bedTimeMinute, wakeTimeHour, wakeTimeMinute, timeAsleep)

        dialogView.findViewById<Button>(R.id.set_bed_time).setOnClickListener {
            bedTimeHour = timePicker.hour
            bedTimeMinute = timePicker.minute
            bedTime.text = String.format("%02d:%02d", bedTimeHour, bedTimeMinute)
            updateSleepDuration(bedTimeHour, bedTimeMinute, wakeTimeHour, wakeTimeMinute, timeAsleep)
        }

        dialogView.findViewById<Button>(R.id.set_wake_time).setOnClickListener {
            wakeTimeHour = timePicker.hour
            wakeTimeMinute = timePicker.minute
            wakeTime.text = String.format("%02d:%02d", wakeTimeHour, wakeTimeMinute)
            updateSleepDuration(bedTimeHour, bedTimeMinute, wakeTimeHour, wakeTimeMinute, timeAsleep)
        }

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(true)
            .create()

        dialogView.findViewById<Button>(R.id.done_button).setOnClickListener {
            findViewById<EditText>(R.id.sleep_input).setText(timeAsleep.text)
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun updateSleepDuration(bedHour: Int, bedMinute: Int, wakeHour: Int, wakeMinute: Int, textView: TextView) {
        val bedTime = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, bedHour)
            set(Calendar.MINUTE, bedMinute)
        }

        val wakeTime = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, wakeHour)
            set(Calendar.MINUTE, wakeMinute)
        }

        val diff = wakeTime.timeInMillis - bedTime.timeInMillis
        val hours = (diff / (1000 * 60 * 60)).toInt()
        val minutes = ((diff / (1000 * 60)) % 60).toInt()
        textView.text = String.format("Time asleep %dh %02dm", hours, minutes)
    }

    private fun setupSingleSelection(buttons: List<ImageButton>) {
        buttons.forEach { button ->
            button.setOnClickListener {
                buttons.forEach { it.isSelected = false }
                button.isSelected = true
                selectedDayEmotion = button.id
                updateButtonSelection(buttons, button)
            }
        }
    }

    private fun setupMultiSelection(buttons: List<ImageButton>, selectedSet: MutableSet<Int>) {
        buttons.forEach { button ->
            button.setOnClickListener {
                if (selectedSet.contains(button.id)) {
                    selectedSet.remove(button.id)
                    button.isSelected = false
                    button.setBackgroundResource(0) // Reset background when unselected
                } else {
                    selectedSet.add(button.id)
                    button.isSelected = true
                    button.setBackgroundResource(R.drawable.selected_background) // Apply custom background when selected
                }
            }
        }
    }

    private fun updateButtonSelection(buttons: List<ImageButton>, selectedButton: ImageButton) {
        buttons.forEach { button ->
            if (button == selectedButton) {
                button.setBackgroundResource(R.drawable.selected_background)
            } else {
                button.setBackgroundResource(0)
            }
        }
    }

    private fun formatDate(date: Date): String {
        val format = SimpleDateFormat("MMMM dd", Locale.getDefault())
        return format.format(date)
    }

    companion object {
        private const val REQUEST_CODE_PICK_IMAGE = 1001
    }
}
