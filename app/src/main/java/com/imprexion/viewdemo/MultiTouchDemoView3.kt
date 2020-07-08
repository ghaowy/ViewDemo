package com.imprexion.viewdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View

/**
 * @author : gongh
 * @date   : 2020/7/6 17:07
 * @desc   : TODO 多点触控 协作型
 */

class MultiTouchDemoView3(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val paths = SparseArray<Path>()

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 10f.px
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        style = Paint.Style.STROKE
        color = Color.parseColor("#FF00FF")
    }

    val path = Path()


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (i in 0 until paths.size()) {
            val valueAt = paths.valueAt(i)
            canvas.drawPath(valueAt, paint)
        }

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                val tmpPath = Path()
                tmpPath.moveTo(event.getX(event.actionIndex), event.getY(event.actionIndex))
                paths.put(event.getPointerId(event.actionIndex), tmpPath)
                invalidate()
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                paths.remove(event.getPointerId(event.actionIndex))
                invalidate()
            }

            MotionEvent.ACTION_MOVE -> {
                val count = event.pointerCount
                for (index in 0 until count) {
                    val x = event.getX(index)
                    val y = event.getY(index)
                    val pointerId = event.getPointerId(index)
                    val path = paths[pointerId]
                    path.lineTo(x, y)
                }
                invalidate()
            }
        }
        return true

    }

}