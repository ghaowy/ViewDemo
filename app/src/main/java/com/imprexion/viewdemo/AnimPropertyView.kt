package com.imprexion.viewdemo

import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View

/**
 * @author : gongh
 * @date   : 2020/5/20 15:20
 * @desc   : TODO
 */
class AnimPropertyView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    var radious = 20f.px
        set(value) {
            field = value
            invalidate()
        }
    var point = PointF(100f.px, 100f.px)
        set(value) {
            field = value
            invalidate()
        }
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#2BD5D5")
        style = Paint.Style.STROKE
        strokeWidth = 40f.px
        strokeCap = Paint.Cap.ROUND

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        canvas.drawCircle(300f.px, 300f.px, radious, paint)
        canvas.drawPoint(point.x, point.y, paint)
    }

    class PointEnvaluator : TypeEvaluator<PointF> {
        override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
            val finalX = startValue.x + (endValue.x - startValue.x) * fraction
            val finalY = startValue.y + (endValue.y - startValue.y) * fraction
            return PointF(finalX, finalY)
        }
    }
}