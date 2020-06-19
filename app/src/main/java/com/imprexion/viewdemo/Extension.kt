package com.imprexion.viewdemo

import android.content.res.Resources
import android.graphics.Paint
import android.util.TypedValue

/**
 * @author : gongh
 * @date   : 2020/5/13 16:02
 * @desc   : TODO
 */

val Float.px: Float
    get() {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this,
            Resources.getSystem().displayMetrics
        )
    }

fun Paint.FontMetrics.string(): String {
    val sb = StringBuilder(32)
    sb.append("FontMetrics(")
        .append(" top: ")
        .append(this.top)
        .append(" decent: ")
        .append(this.descent)
        .append(" ascent: ")
        .append(this.ascent)
        .append(" bottom: ")
        .append(this.bottom)
        .append(" leading: ")
        .append(this.leading)
    return sb.toString()
}