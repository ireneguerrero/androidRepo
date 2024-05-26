package com.ghostly

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment

class RegisterDayDialogFragment(private val onEmotionSelected: (Int) -> Unit) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_register_day, container, false)

        view.findViewById<ImageButton>(R.id.happy_button).setOnClickListener {
            onEmotionSelected(R.drawable.muyfeliz)
            dismiss()
        }

        view.findViewById<ImageButton>(R.id.smile_button).setOnClickListener {
            onEmotionSelected(R.drawable.feliz)
            dismiss()
        }

        view.findViewById<ImageButton>(R.id.neutral_button).setOnClickListener {
            onEmotionSelected(R.drawable.normal)
            dismiss()
        }

        view.findViewById<ImageButton>(R.id.sad_button).setOnClickListener {
            onEmotionSelected(R.drawable.triste)
            dismiss()
        }

        view.findViewById<ImageButton>(R.id.verysad_button).setOnClickListener {
            onEmotionSelected(R.drawable.muytriste)
            dismiss()
        }

        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setGravity(Gravity.CENTER)
        val params: WindowManager.LayoutParams? = dialog?.window?.attributes
        params?.verticalMargin = 0.1f
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }
}
