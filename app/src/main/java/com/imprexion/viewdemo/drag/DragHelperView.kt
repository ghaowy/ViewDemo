package com.imprexion.viewdemo.drag

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.customview.widget.ViewDragHelper

/**
 * @author : gongh
 * @date   : 2020/7/8 14:43
 * @desc   : TODO
 */
const val COL = 2
const val ROW = 3

class DragHelperView(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    var childWidth = 0
    var childHeight = 0
    val callback = DragCallback()
    val dragHelperView = ViewDragHelper.create(this, callback)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec);
        val height = MeasureSpec.getSize(heightMeasureSpec);
        childWidth = width / COL
        childHeight = height / ROW
        measureChildren(
            MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(childHeight, childHeight)
        )
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return dragHelperView.shouldInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        dragHelperView.processTouchEvent(event)
        return true
    }

    override fun computeScroll() {
        if (dragHelperView.continueSettling(true)) {
            postInvalidateOnAnimation()
        }
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft = 0
        var childTop = 0

        for (index in 0 until childCount) {
            val childAt = getChildAt(index)
            childLeft = (index % COL) * childWidth

            childTop = (index / COL) * childHeight

            childAt.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight)
        }
    }


    inner class DragCallback : ViewDragHelper.Callback() {
        var dragLeft = 0
        var dragTop = 0
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return true
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top.coerceAtLeast(0).coerceAtMost(height)
        }

        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            return left.coerceAtMost(width).coerceAtLeast(0)
        }

        override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
            capturedChild.elevation++
            dragLeft = capturedChild.left;
            dragTop = capturedChild.top
        }

        override fun onViewDragStateChanged(state: Int) {
            if (state == ViewDragHelper.STATE_IDLE) {
                dragHelperView.capturedView!!.elevation--
            }
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            dragHelperView.settleCapturedViewAt(dragLeft, dragTop)
//            //更新下一帧的绘制
            postInvalidateOnAnimation()
        }


    }
}