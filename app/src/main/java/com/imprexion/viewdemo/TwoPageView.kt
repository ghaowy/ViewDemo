package com.imprexion.viewdemo

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.Scroller
import kotlin.math.abs

/**
 * @author : gongh
 * @date   : 2020/7/7 15:32
 * @desc   : 只有两个子View 的自定义group
 */

class TwoPageView(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    var downX = 0f
    var downY = 0f

    var offsetX = 0f
    var originalX = 0f

    val scroller = Scroller(context)
    private val velocityTracker = VelocityTracker.obtain()
    private val viewConfiguration = ViewConfiguration.get(context)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // 以传入的宽高限制测量所有子View 的大小
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var offsetX = 0
        val offsetY = 0
        for (index in 0 until childCount) {
            val childAt = getChildAt(index)
            childAt.layout(offsetX, offsetY, offsetX + width, offsetY + height)
            offsetX += width
        }
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
                originalX = offsetX
            }

            MotionEvent.ACTION_MOVE -> {
                offsetX = (downX - event.x) + originalX
                offsetX = offsetX.coerceAtLeast(0f).coerceAtMost(width.toFloat())
                scrollTo(offsetX.toInt(), 0)
            }

            MotionEvent.ACTION_UP -> {
                velocityTracker.computeCurrentVelocity(
                    1000,
                    viewConfiguration.scaledMaximumFlingVelocity.toFloat()
                )

                val xVelocity = velocityTracker.xVelocity
                val targetPage =
                    if (abs(xVelocity) < viewConfiguration.scaledMinimumFlingVelocity) {
                        if (scrollX > width / 2) 1 else 0
                    } else {
                        if (xVelocity < 0) 1 else 0
                    }
                val scrollDistance = if (targetPage == 1) width - offsetX else -offsetX
                scroller.startScroll(offsetX.toInt(), 0, scrollDistance.toInt(), 0)
                postInvalidateOnAnimation()
            }
        }
        return true
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        var result = false
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downX = ev.x
                downY = ev.y
                originalX = offsetX
            }

            MotionEvent.ACTION_MOVE -> {
                if (abs(ev.x - downX) > viewConfiguration.scaledPagingTouchSlop) {
                    requestDisallowInterceptTouchEvent(true)
                    result = true
                }
            }
        }
        return result

    }

    override fun computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.currX, scroller.currY)
            postInvalidateOnAnimation()
        }
//        super.computeScroll()
    }

}