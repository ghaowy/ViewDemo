package com.imprexion.viewdemo

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat

/**
 * @author : gongh
 * @date   : 2020/6/28 17:49
 * @desc   : TODO
 */

const val EXTRA_SCALE = 1.5f

class ScaleDoubleScrollView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val scaleListener = ScaleGestureListener()
    private val scaleGesture = ScaleGestureDetector(context, scaleListener)
    private val gestureListener = SlideGestureListener()
    private val gestureDetector = GestureDetectorCompat(context, gestureListener)
    val bitmap = Util.getImageBitmap(resources, 400)
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val refreshRunnable = RefreshRunnable()
    val scroller = OverScroller(context)
    var originalOffsetX = 0f
    var originalOffsetY = 0f
    var offsetX = 0f
    var offsetY = 0f
    var smallScale = 0f
    var bigScale = 0f
    var isBig = false
    private var currentScale = 0f
        set(value) {
            field = value
            invalidate()
        }

    val scaleAnimator: ObjectAnimator by lazy {
        ObjectAnimator.ofFloat(this, "currentScale", smallScale, bigScale)
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        originalOffsetX = (w - bitmap.width) / 2f
        originalOffsetY = (h - bitmap.height) / 2f

        if (bitmap.width / bitmap.height > w / h) {
            smallScale = w / bitmap.width.toFloat()
            bigScale = h / bitmap.height.toFloat() + EXTRA_SCALE
        } else {
            smallScale = h.toFloat() / bitmap.height
            bigScale = w / bitmap.width.toFloat() + EXTRA_SCALE
        }
        currentScale = smallScale
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.translate(
            offsetX * (currentScale - smallScale) / (bigScale - smallScale),
            offsetY * (currentScale - smallScale) / (bigScale - smallScale)
        )
        canvas.scale(currentScale, currentScale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint)
    }


    private fun fixY() {
        if (offsetY > (bitmap.height * bigScale - height) / 2f) {
            offsetY = ((bitmap.height * bigScale - height) / 2f)
        }

        if (offsetY < -(bitmap.height * bigScale - height) / 2f) {
            offsetY = -(bitmap.height * bigScale - height) / 2f
        }
    }

    private fun fixX() {
        if (offsetX > (bitmap.width * bigScale - width) / 2f) {
            offsetX = ((bitmap.width * bigScale - width) / 2f)
        }

        if (offsetX < -(bitmap.width * bigScale - width) / 2f) {
            offsetX = -((bitmap.width * bigScale - width) / 2f)
        }
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        return gestureDetector.onTouchEvent(event)
        return scaleGesture.onTouchEvent(event)
    }

    inner class SlideGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onShowPress(e: MotionEvent?) {
        }

        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            return false
        }

        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (isBig) {
                scroller.fling(
                    offsetX.toInt(),
                    offsetY.toInt(),
                    velocityX.toInt(),
                    velocityY.toInt(),
                    (-((bitmap.width * bigScale - width) / 2f)).toInt(),
                    (((bitmap.width * bigScale - width) / 2f).toInt()),
                    (-(bitmap.height * bigScale - height) / 2f).toInt(),
                    (((bitmap.height * bigScale - height) / 2f).toInt())
                )
                postOnAnimation(refreshRunnable)
            }

            return false
        }


        override fun onLongPress(e: MotionEvent?) {

        }

        override fun onDoubleTap(e: MotionEvent): Boolean {
            // 做一个偏移
            if (isBig) {
                scaleAnimator.reverse()
            } else {
                offsetX = (e.x - width / 2) * (1 - bigScale / smallScale)
                offsetY = (e.y - height / 2) * (1 - bigScale / smallScale)

                fixX()
                fixY()
                scaleAnimator.start()
            }
            isBig = !isBig
            return true
        }

        override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
            return false
        }

        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            return true
        }

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            if (isBig) {
                offsetX -= distanceX
                offsetY -= distanceY
                fixX()
                fixY()
                invalidate()
            }
            return false
        }

    }

    inner class RefreshRunnable : Runnable {
        override fun run() {
            if (scroller.computeScrollOffset()) {
                offsetX = scroller.currX.toFloat()
                offsetY = scroller.currY.toFloat()
                invalidate()
                postOnAnimation(this)
            }
        }
    }

    inner class ScaleGestureListener : ScaleGestureDetector.OnScaleGestureListener {
        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            offsetX = (detector.focusX - width / 2) * (1 - bigScale / smallScale)
            offsetY = (detector.focusY - height / 2) * (1 - bigScale / smallScale)
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector?) {
        }

        override fun onScale(detector: ScaleGestureDetector): Boolean {
//            detector.scaleFactor
            val tmp = currentScale * detector.scaleFactor
            if (tmp < smallScale || tmp > bigScale) {
                return false
            } else {
                currentScale *= detector.scaleFactor
            }
            return true
        }

    }


}