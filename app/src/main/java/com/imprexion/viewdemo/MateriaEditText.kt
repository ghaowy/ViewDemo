package com.imprexion.viewdemo

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

/**
 * @author : gongh
 * @date   : 2020/6/5 10:22
 * @desc   : TODO
 */
val TEXT_SIZE = 16f.px
val TEXT_MARGIN = 10f.px
val HORIZONTAL_OFFSET = 5f.px
val VERTICAL_OFFSET = 15f.px

class MateriaEditText(context: Context?, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {
    var isLabelShow = false
    public var fac = 0.0f
    private val animator by lazy {
        ObjectAnimator.ofFloat(this, "fac", 0f, 1f)
    }

    init {
        setPadding(paddingLeft, (TEXT_SIZE + TEXT_MARGIN).toInt(), paddingRight, paddingBottom)
    }

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = TEXT_SIZE
    }



    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        if (isLabelShow && text.isNullOrEmpty()) {
            isLabelShow = false
            animator.reverse()
        } else if (!isLabelShow && !text.isNullOrEmpty()) {
            isLabelShow = true
            animator.start()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.alpha = (0xff * fac).toInt()

        canvas.drawText(
            hint.toString(),
            HORIZONTAL_OFFSET,
            VERTICAL_OFFSET + (1 - fac) * TEXT_MARGIN,
            paint
        )
    }


}