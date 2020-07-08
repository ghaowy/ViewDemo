package com.imprexion.viewdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

/**
 * @author : gongh
 * @date   : 2020/7/6 17:07
 * @desc   : TODO
 */

class MultiTouchDemoView1(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val bitmap = Util.getImageBitmap(resources, 300)
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    var offsetX = 0f
    var offsetY = 0f

    var downX = 0f
    var downY = 0f

    var originalX = 0f
    var originalY = 0f

    var trackingId = 0

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.actionMasked) {
            //
            MotionEvent.ACTION_DOWN -> {
                trackingId = event.getPointerId(event.actionIndex)
                downX = event.getX(trackingId)
                downY = event.getY(trackingId)
                originalX = offsetX
                originalY = offsetY
            }

            MotionEvent.ACTION_POINTER_DOWN -> {
                trackingId = event.getPointerId(event.actionIndex)
                downX = event.getX(event.actionIndex)
                downY = event.getY(event.actionIndex)
                originalX = offsetX
                originalY = offsetY
            }

            MotionEvent.ACTION_MOVE -> {
                val index = event.findPointerIndex(trackingId)
                offsetX = event.getX(index) - downX + originalX
                offsetY = event.getY(index) - downY + originalY
                invalidate()
            }

            MotionEvent.ACTION_POINTER_UP -> {

                if (event.getPointerId(event.actionIndex) == trackingId) {
                    val index = if (event.actionIndex == event.pointerCount - 1) {
                        event.pointerCount - 2
                    } else {
                        event.pointerCount - 1
                    }
                    Log.d(TAG, "onTouchEvent(): index= $index")
                    trackingId = event.getPointerId(index)

                    downX = event.getX(event.findPointerIndex(trackingId))
                    downY = event.getY(event.findPointerIndex(trackingId))
                    originalX = offsetX
                    originalY = offsetY
                }

            }
        }
        return true
    }

}