package com.imprexion.viewdemo

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


/**
 * @author : gongh
 * @date   : 2020/6/19 14:18
 * @desc   : TODO
 */
var RADIUS_CIRCLE = 200f.px
var SIZE = 100f.px + RADIUS_CIRCLE + 100f.px

class ViewSizeDemo(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

//    // 强制修改View的大小 父View 无法控制
//    override fun layout(l: Int, t: Int, r: Int, b: Int) {
//        super.layout(l, t, l + 200, t + 200)
//
//    }

    private val bitmap by lazy {
        BitmapFactory.decodeResource(resources, R.mipmap.dialog_img_error)
    }

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = resolveSize(SIZE.toInt(), widthMeasureSpec);
        val height = resolveSize(SIZE.toInt(), heightMeasureSpec);
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        canvas.drawBitmap(bitmap, 0f, 0f, paint)

        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), 200f.px, paint)
    }
}