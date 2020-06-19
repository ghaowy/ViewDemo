package com.imprexion.viewdemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * @author : gongh
 * @date   : 2020/5/15 14:23
 * @desc   : TODO
 */
val CORNER_PATH_EFFECT = CornerPathEffect(30f.px)
val DISCREATE_PATH_EFFECT = DiscretePathEffect(2f.px, 2f.px)
val DASH_PATH_EFFECT = DashPathEffect(floatArrayOf(2f.px, 2f.px, 2f.px, 2f.px), 40f.px)
val DASH_PATH = Path().apply {
    addCircle(0f, 0f, 10f.px, Path.Direction.CCW)
    addRect((-10f).px, (-10f).px, 10f.px, 10f.px, Path.Direction.CW)
    addCircle(0f, 0f, 2f.px, Path.Direction.CCW)
}
val PATH_PATH_EFFECT = PathDashPathEffect(DASH_PATH, 50f, 0f, PathDashPathEffect.Style.ROTATE)
val SUM_PATH_EFFECT = SumPathEffect(CORNER_PATH_EFFECT, DISCREATE_PATH_EFFECT)
val COMPOSE_PATH_EFFECT = ComposePathEffect(CORNER_PATH_EFFECT, DISCREATE_PATH_EFFECT)

class DashPathEffectView : View {
    private lateinit var path: Path
    private lateinit var destPath: Path
    private var centerX = 0f
    private var centerY = 0f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#2196F3")
        strokeWidth = 2f.px
        style = Paint.Style.STROKE //只描边

    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        centerX = w / 2f
        centerY = h / 2f
        path = Path()
        destPath = Path()
//        path.moveTo(0f, 0f)
//        path.lineTo(centerX, centerY)
//        path.lineTo(centerX - 200, centerY + 200)
//        path.close()
        paint.textSize = 24f.px
        paint.getTextPath("hello world", 0, 11, 100f, 200f, path)
//        paint.style = Paint.Style.STROKE
        paint.getFillPath(path, destPath)
        paint.style = Paint.Style.STROKE
//        path.lineTo(centerX, centerY)
//        path.close()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        paint.pathEffect = DASH_PATH_EFFECT
        canvas.drawPath(destPath, paint)
//        canvas.drawCircle(200f.px, 200f.px, 150f.px, paint)
    }
}