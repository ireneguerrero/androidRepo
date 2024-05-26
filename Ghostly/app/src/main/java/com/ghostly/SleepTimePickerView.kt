package com.example.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.*

class SleepTimePickerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var startAngle = 0.0
    private var endAngle = 0.0
    private var radius = 0.0f
    private var centerX = 0.0f
    private var centerY = 0.0f

    private val paint = Paint().apply {
        color = Color.parseColor("#F5F5DC")
        strokeWidth = 5f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = (w / 2).toFloat()
        centerY = (h / 2).toFloat()
        radius = (min(w, h) / 2 * 0.8).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw the circle
        canvas.drawCircle(centerX, centerY, radius, paint)

        // Draw start handle
        val startX = (centerX + radius * cos(startAngle)).toFloat()
        val startY = (centerY + radius * sin(startAngle)).toFloat()
        canvas.drawCircle(startX, startY, paint.strokeWidth * 2, paint)

        // Draw end handle
        val endX = (centerX + radius * cos(endAngle)).toFloat()
        val endY = (centerY + radius * sin(endAngle)).toFloat()
        canvas.drawCircle(endX, endY, paint.strokeWidth * 2, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x - centerX
        val y = event.y - centerY
        val angle = atan2(y.toDouble(), x.toDouble())

        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                if (isNearHandle(x.toFloat(), y.toFloat(), startAngle)) {
                    startAngle = angle
                    invalidate()
                    return true
                } else if (isNearHandle(x.toFloat(), y.toFloat(), endAngle)) {
                    endAngle = angle
                    invalidate()
                    return true
                }
            }
        }
        return super.onTouchEvent(event)
    }

    private fun isNearHandle(x: Float, y: Float, handleAngle: Double): Boolean {
        val handleX = centerX + radius * cos(handleAngle).toFloat()
        val handleY = centerY + radius * sin(handleAngle).toFloat()
        val distance = sqrt((x - handleX).pow(2) + (y - handleY).pow(2))
        return distance < paint.strokeWidth * 2
    }
}
