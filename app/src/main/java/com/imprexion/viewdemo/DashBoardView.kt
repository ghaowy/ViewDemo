package com.imprexion.viewdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

/**
 * @author : gongh
 * @date   : 2020/5/13 15:47
 * @desc   : TODO
 */
const val RADIUS = 300f
const val MARK_LENGTH = 50f
const val MARK_INDEX = 4
val ANGLE_LIST = floatArrayOf(80f, 30f, 50f, 70f, 130f)
val COLOR_LIST =
    intArrayOf(
        Color.parseColor("#FF0000")
        , Color.parseColor("#E66B1A")
        , Color.parseColor("#2BD5D5")
        , Color.parseColor("#BD1AE6")
        , Color.parseColor("#808080")
    )

class DashBoardView(context: Context, attributes: AttributeSet?) : View(context, attributes) {
    private var centerX: Float = 0f
    private var centerY: Float = 0f

    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#FF00FF")
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2f
        centerY = h / 2f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var startAngle = 0f
        for (index in ANGLE_LIST.indices) {
            if (index == MARK_INDEX) {
                canvas?.save()
                canvas?.translate(
                    MARK_LENGTH * cos(Math.toRadians(((startAngle + ANGLE_LIST[index] / 2).toDouble()))).toFloat(),
                    MARK_LENGTH * sin(Math.toRadians(((startAngle + ANGLE_LIST[index] / 2).toDouble()))).toFloat()
                )
            }
            paint.color = COLOR_LIST[index]
            canvas?.drawArc(
                centerX - RADIUS,
                centerY - RADIUS,
                centerX + RADIUS,
                centerY + RADIUS,
                startAngle,
                ANGLE_LIST[index],
                true,
                paint
            )
            startAngle += ANGLE_LIST[index]
            if (index == MARK_INDEX) {
                canvas?.restore()
            }
        }
    }
}