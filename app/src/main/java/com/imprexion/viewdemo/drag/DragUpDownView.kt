package com.imprexion.viewdemo.drag

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.customview.widget.ViewDragHelper
import com.imprexion.viewdemo.R

/**
 * @author : gongh
 * @date   : 2020/7/8 17:46
 * @desc   : TODO
 */
const val TAG = "DragUpDownView"

class DragUpDownView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    val callback = DragCallback()


    private val viewDragHelper by lazy { ViewDragHelper.create(this, callback) }


    private lateinit var childView: View


    override fun onTouchEvent(event: MotionEvent): Boolean {
        viewDragHelper.processTouchEvent(event)
        return true
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        childView = findViewById<LinearLayout>(R.id.drag_view)
    }

    override fun computeScroll() {
        if (viewDragHelper.continueSettling(true)) {
            postInvalidateOnAnimation()
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return viewDragHelper.shouldInterceptTouchEvent(ev)
    }

    inner class DragCallback : ViewDragHelper.Callback() {
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return childView == child
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top.coerceAtLeast(0).coerceAtMost(height)
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
//            if (abs(yvel) > ViewConfiguration.get(context).scaledMinimumFlingVelocity) {
//                if (0 < yvel) {
//                    viewDragHelper.settleCapturedViewAt(0, height - releasedChild.height)
//                } else viewDragHelper.settleCapturedViewAt(0, 0)
//            } else {
//                if (releasedChild.top < height / 2) {
//                    viewDragHelper.settleCapturedViewAt(0, 0)
//                } else viewDragHelper.settleCapturedViewAt(0, height - releasedChild.height)
//            }
            if (releasedChild.top > height /2) {
                viewDragHelper.settleCapturedViewAt(0, height - releasedChild.height)
            }else{
                viewDragHelper.settleCapturedViewAt(0, 0)
            }
            Log.d(TAG, "onViewReleased(): xvel= $xvel + yvel= $yvel")

            postInvalidateOnAnimation()
        }


    }
}