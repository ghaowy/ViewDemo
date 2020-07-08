package com.imprexion.viewdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * @author : gongh
 * @date   : 2020/7/6 17:07
 * @desc   : TODO 多点触控 协作型
 */

class MultiTouchDemoView2(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val bitmap = Util.getImageBitmap(resources, 300)
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    var offsetX = 0f
    var offsetY = 0f

    var downX = 0f
    var downY = 0f

    var focusX = 0f
    var focusY = 0f

    var originalX = 0f
    var originalY = 0f


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {

        var sumX = 0f
        var sumY = 0f
        val isPointUp = event.actionMasked == MotionEvent.ACTION_POINTER_UP
        for (i in 0 until event.pointerCount) {
            if (isPointUp && event.actionIndex == i) {
                continue
            }
            sumX += event.getX(i)
            sumY += event.getY(i)

        }
        var count = event.pointerCount
        if (isPointUp) count--
        focusX = sumX / count
        focusY = sumY / count


        when (event.actionMasked) {
            //
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN, MotionEvent.ACTION_POINTER_UP -> {
                downX = focusX
                downY = focusY
                originalX = offsetX
                originalY = offsetY
            }


            MotionEvent.ACTION_MOVE -> {
                offsetX = focusX - downX + originalX
                offsetY = focusY - downY + originalY
                invalidate()
            }
        }
        return true
    }

}