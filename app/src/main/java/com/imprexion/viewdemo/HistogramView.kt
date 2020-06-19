package com.imprexion.viewdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

/**
 * @author : gongh
 * @date   : 2020/5/14 14:56
 * @desc   : TODO
 */

val VALUE_LIST = floatArrayOf(60f, 70f, 90f, 80f)
val NAME_LIST = arrayOf("nihao", "gonghao", "zhangzhitao", "shenguozhao")
const val MAX_VALUE = 100
val PADDING = 20f.px
val WIDTH_VIEW = 40f.px

class HistogramView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private lateinit var bound: Rect
    private var space = 0f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#22DDDD")
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bound = Rect(
            PADDING.toInt(), PADDING.toInt(), (w - 2 * PADDING).toInt(),
            (h - 2 * PADDING).toInt()
        )
        space = if (w > WIDTH_VIEW * VALUE_LIST.size) {
            (w - WIDTH_VIEW * VALUE_LIST.size - 4 * PADDING) / VALUE_LIST.size
        } else {
            10f
        }

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawTest(canvas)
//        drawHistogram(canvas)
    }

    private fun drawTest(canvas: Canvas) {
        paint.apply {
            strokeWidth = 50f.px
            strokeCap = Paint.Cap.ROUND
        }
        canvas.drawPoint(200f.px, 200f.px, paint)
    }

    private fun drawHistogram(canvas: Canvas) {
        canvas.drawLine(
            bound.left.toFloat(),
            bound.top.toFloat(), bound.left.toFloat(), bound.bottom.toFloat(), paint
        )
        canvas.drawLine(
            bound.left.toFloat(),
            bound.bottom.toFloat(), bound.right.toFloat(), bound.bottom.toFloat(), paint
        )
        var sumLeft = 0f
        for (index in VALUE_LIST.indices) {
            canvas.drawRect(
                space + sumLeft,
                ((bound.bottom - bound.top) * VALUE_LIST[index] / MAX_VALUE),
                space + sumLeft + WIDTH_VIEW,
                bound.bottom.toFloat(),
                paint
            )
            sumLeft += (space + WIDTH_VIEW)

            canvas.drawText(
                NAME_LIST[index],
                sumLeft - WIDTH_VIEW,
                (bound.bottom + 20).toFloat(),
                paint
            )
        }
    }
}