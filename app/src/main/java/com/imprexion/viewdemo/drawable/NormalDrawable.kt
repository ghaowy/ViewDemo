package com.imprexion.viewdemo.drawable

import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.graphics.toColorInt
import com.imprexion.viewdemo.px

/**
 * @author : gongh
 * @date   : 2020/6/2 13:47
 * @desc   : TODO
 */
class NormalDrawable : Drawable() {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = "#FF00FF".toColorInt()
        style = Paint.Style.FILL
    }
    private val path = Path().apply {
        addCircle(
            ((bounds.left + bounds.right) / 2).toFloat(),
            (bounds.top + bounds.bottom / 2).toFloat(),
            100f.px, Path.Direction.CCW
        )

        addRect(
            RectF(
                bounds.left.toFloat(), bounds.top.toFloat(), bounds.right.toFloat(),
                bounds.bottom.toFloat()
            ), Path.Direction.CCW
        )
    }

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun getAlpha(): Int {
        return paint.alpha
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSPARENT
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    override fun getColorFilter(): ColorFilter? {
        return paint.colorFilter
    }

}