package com.imprexion.viewdemo

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.imprexion.viewdemo.drawable.NormalDrawable

/**
 * @author : gongh
 * @date   : 2020/6/2 13:44
 * @desc   : TODO
 */
class DrawableView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val drawable = NormalDrawable()


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawable.setBounds(0, 0, width, height)
        drawable.draw(canvas)
    }

}