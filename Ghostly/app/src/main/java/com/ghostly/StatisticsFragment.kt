package com.ghostly

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import android.os.Handler
import android.os.Looper

//Muestra las estadísticas de un mes específico
class StatisticsFragment : Fragment() {

    private lateinit var db: FirebaseFirestore
    private var monthOffset: Int = 0

    private lateinit var happyCountText: TextView
    private lateinit var happyPercentText: TextView
    private lateinit var smileCountText: TextView
    private lateinit var smilePercentText: TextView
    private lateinit var neutralCountText: TextView
    private lateinit var neutralPercentText: TextView
    private lateinit var sadCountText: TextView
    private lateinit var sadPercentText: TextView
    private lateinit var verysadCountText: TextView
    private lateinit var verysadPercentText: TextView

    private lateinit var mostRecordedEmotionLayout: LinearLayout
    private lateinit var mostRecordedEmotionImage: ImageView
    private lateinit var mostRecordedEmotionText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            monthOffset = it.getInt(ARG_MONTH_OFFSET)
        }
        db = FirebaseFirestore.getInstance() // Initialize Firestore here
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_statistics, container, false)

        happyCountText = view.findViewById(R.id.happy_count)
        happyPercentText = view.findViewById(R.id.happy_percent)
        smileCountText = view.findViewById(R.id.smile_count)
        smilePercentText = view.findViewById(R.id.smile_percent)
        neutralCountText = view.findViewById(R.id.neutral_count)
        neutralPercentText = view.findViewById(R.id.neutral_percent)
        sadCountText = view.findViewById(R.id.sad_count)
        sadPercentText = view.findViewById(R.id.sad_percent)
        verysadCountText = view.findViewById(R.id.verysad_count)
        verysadPercentText = view.findViewById(R.id.verysad_percent)

        mostRecordedEmotionLayout = view.findViewById(R.id.most_recorded_emotion_layout)
        mostRecordedEmotionImage = view.findViewById(R.id.most_recorded_emotion_image)
        mostRecordedEmotionText = view.findViewById(R.id.most_recorded_emotion_text)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateStatistics()
    }

    fun updateStatistics() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, monthOffset)
        val month = calendar.get(Calendar.MONTH) + 1 // Firestore stores months as 1-12
        val year = calendar.get(Calendar.YEAR)

        Log.d("StatisticsFragment", "Fetching data for month: $month, year: $year")

        db.collection("Registro")
            .whereEqualTo("month", month)
            .whereEqualTo("year", year)
            .get()
            .addOnSuccessListener { documents ->
                Log.d("StatisticsFragment", "Documents fetched: ${documents.size()}")
                if (documents.isEmpty) {
                    Log.d("StatisticsFragment", "No documents found")
                } else {
                    var happyCount = 0
                    var smileCount = 0
                    var neutralCount = 0
                    var sadCount = 0
                    var verysadCount = 0

                    for (document in documents) {
                        val emotion = document.getLong("numeroDia")?.toInt()
                        Log.d("StatisticsFragment", "Document emotion: $emotion")
                        when (emotion) {
                            0 -> happyCount++
                            1 -> smileCount++
                            2 -> neutralCount++
                            3 -> sadCount++
                            4 -> verysadCount++
                        }
                    }

                    val totalCount = happyCount + smileCount + neutralCount + sadCount + verysadCount
                    Log.d("StatisticsFragment", "Counts - Happy: $happyCount, Smile: $smileCount, Neutral: $neutralCount, Sad: $sadCount, VerySad: $verysadCount, Total: $totalCount")

                    val handler = Handler(Looper.getMainLooper())
                    handler.post {
                        happyCountText.text = "$happyCount ${if (happyCount == 1) "día" else "días"}"
                        smileCountText.text = "$smileCount ${if (smileCount == 1) "día" else "días"}"
                        neutralCountText.text = "$neutralCount ${if (neutralCount == 1) "día" else "días"}"
                        sadCountText.text = "$sadCount ${if (sadCount == 1) "día" else "días"}"
                        verysadCountText.text = "$verysadCount ${if (verysadCount == 1) "día" else "días"}"

                        happyPercentText.text = if (totalCount > 0) "${(happyCount * 100.0 / totalCount).toInt()}%" else "0%"
                        smilePercentText.text = if (totalCount > 0) "${(smileCount * 100.0 / totalCount).toInt()}%" else "0%"
                        neutralPercentText.text = if (totalCount > 0) "${(neutralCount * 100.0 / totalCount).toInt()}%" else "0%"
                        sadPercentText.text = if (totalCount > 0) "${(sadCount * 100.0 / totalCount).toInt()}%" else "0%"
                        verysadPercentText.text = if (totalCount > 0) "${(verysadCount * 100.0 / totalCount).toInt()}%" else "0%"

                        displayMostRecordedEmotion(
                            happyPercentText.text.toString().removeSuffix("%").toInt(),
                            smilePercentText.text.toString().removeSuffix("%").toInt(),
                            neutralPercentText.text.toString().removeSuffix("%").toInt(),
                            sadPercentText.text.toString().removeSuffix("%").toInt(),
                            verysadPercentText.text.toString().removeSuffix("%").toInt()
                        )
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.e("StatisticsFragment", "Error getting documents: ", exception)
                val handler = Handler(Looper.getMainLooper())
                handler.post {
                    happyCountText.text = "0 días"
                    happyPercentText.text = "0%"
                    smileCountText.text = "0 días"
                    smilePercentText.text = "0%"
                    neutralCountText.text = "0 días"
                    neutralPercentText.text = "0%"
                    sadCountText.text = "0 días"
                    sadPercentText.text = "0%"
                    verysadCountText.text = "0 días"
                    verysadPercentText.text = "0%"
                }
            }
    }

    private fun displayMostRecordedEmotion(happyPercent: Int, smilePercent: Int, neutralPercent: Int, sadPercent: Int, verysadPercent: Int) {
        val maxPercent = listOf(happyPercent, smilePercent, neutralPercent, sadPercent, verysadPercent).maxOrNull() ?: 0
        val emotionImageRes: Int
        val emotionText: String

        when (maxPercent) {
            happyPercent -> {
                emotionImageRes = R.drawable.muyfeliz
                emotionText = "La felicidad es contagiosa. ¡Sigue irradiando tu luz y alegrando a quienes te rodean!"
            }
            smilePercent -> {
                emotionImageRes = R.drawable.feliz
                emotionText = "La felicidad que sientes ahora es el resultado de tu esfuerzo y perseverancia. ¡Celebra cada logro!"
            }
            neutralPercent -> {
                emotionImageRes = R.drawable.normal
                emotionText = "No importa lo lento que vayas, siempre y cuando no te detengas."
            }
            sadPercent -> {
                emotionImageRes = R.drawable.triste
                emotionText = "Cada pequeño paso cuenta, no subestimes el poder de seguir avanzando."
            }
            verysadPercent -> {
                emotionImageRes = R.drawable.muytriste
                emotionText = "Permítete sentir, pero no te quedes atrapado en el dolor. La felicidad también te está esperando."
            }
            else -> {
                emotionImageRes = R.drawable.nada
                emotionText = "¡Registra tu mes!"
            }
        }

        mostRecordedEmotionImage.setImageResource(emotionImageRes)
        mostRecordedEmotionText.text = emotionText
        mostRecordedEmotionLayout.visibility = View.VISIBLE
    }

    companion object {
        private const val ARG_MONTH_OFFSET = "month_offset"

        @JvmStatic
        fun newInstance(monthOffset: Int) =
            StatisticsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_MONTH_OFFSET, monthOffset)
                }
            }
    }
}
