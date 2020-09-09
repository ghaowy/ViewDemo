package com.imprexion.viewdemo.drag

import android.content.ClipData
import android.content.Context
import android.util.AttributeSet
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat

/**
 * @author : gongh
 * @date   : 2020/7/8 14:43
 * @desc   : TODO
 */
class DragListenerView(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    var childWidth = 0
    var childHeight = 0
    val dragListener = DemoDragListener()
    val arrayList = ArrayList<View>()
    lateinit var dragView: View

    init {
        isChildrenDrawingOrderEnabled = true
    }

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

    override fun onFinishInflate() {
        super.onFinishInflate()
        for (index in 0 until childCount) {
            val childAt = getChildAt(index)
            arrayList.add(childAt)
            childAt.setOnLongClickListener {
                dragView = it
                //                ViewCompat.startDragAndDrop(it, null, DragShadowBuilder(it), it, 0)
//                it.startDrag(null, DragShadowBuilder(it), it, 0)
                ViewCompat.startDragAndDrop(
                    it,
                    ClipData.newPlainText("name", "drag"),
                    DragShadowBuilder(it),
                    it,
                    0
                )
                true
            }

            childAt.setOnDragListener(dragListener)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {


        for (index in 0 until childCount) {
            val childAt = getChildAt(index)
            childAt.layout(0, 0, childWidth, childHeight)
            childAt.translationX = ((index % COL) * childWidth).toFloat()
            childAt.translationY = ((index / COL) * childHeight).toFloat()
        }
    }


    inner class DemoDragListener : View.OnDragListener {
        override fun onDrag(v: View, event: DragEvent): Boolean {
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {

                    if (event.localState == v) {
                        v.visibility = View.INVISIBLE
                    }
                }

                DragEvent.ACTION_DRAG_ENDED -> {
                    if (event.localState == v) {
                        v.visibility = View.VISIBLE
                    }
                }

                DragEvent.ACTION_DRAG_ENTERED -> {
                    if (event.localState != v) {
                        sortView(v)
                    }
                }
            }

            return true
        }

        private fun sortView(targetView: View) {
            var targetIndex = -1
            var dragIndex = -1

            for (index in 0 until childCount) {
                val childAt = arrayList[index]
                if (childAt == targetView) {
                    targetIndex = index
                } else if (dragView == childAt) {
                    dragIndex = index
                }
            }
            if (dragIndex != targetIndex) {
                arrayList.removeAt(dragIndex)
                arrayList.add(targetIndex, dragView)
            }


            for (index in 0 until arrayList.size) {
                val childAt = arrayList[index]
                childAt.animate().translationX(((index % COL) * childWidth).toFloat())
                    .translationY(((index / COL) * childHeight).toFloat()).duration = 150
            }
        }

    }
}