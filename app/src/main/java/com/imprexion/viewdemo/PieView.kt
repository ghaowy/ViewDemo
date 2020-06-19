package com.imprexion.viewdemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

/**
 * @author : gongh
 * @date   : 2020/5/13 16:59
 * @desc   : TODO
 */

const val ANGLE_START = 150f
const val OPEN_ANGLE = 120
private val DASH_WIDTH = 5f.px
private val DASH_HEIGHT = 10f.px
private const val MARK_COUNT = 20
private const val INDICATOR_INDEX = 0
private const val INDICATOR_LENGTH = 200

class PieView : View {
    private var centerX = 0f
    private var centerY = 0f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
    }
    private val dash = Path().apply {
        addRect(0f, 0f, DASH_WIDTH, DASH_HEIGHT, Path.Direction.CCW)
    }

    private lateinit var path: Path
    private lateinit var pathDashPathEffect: PathDashPathEffect


    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2f
        centerY = h / 2f
        path = Path().apply {
            addArc(
                centerX - RADIUS, centerY - RADIUS, centerX + RADIUS, centerY + RADIUS,
                ANGLE_START, (360 - OPEN_ANGLE).toFloat()
            )
        }

        pathDashPathEffect =
            PathDashPathEffect(
                dash,
                (PathMeasure(path, false).length - DASH_WIDTH) / MARK_COUNT,
                0f,
                PathDashPathEffect.Style.ROTATE
            )

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
        paint.pathEffect = pathDashPathEffect
        canvas.drawPath(path, paint)
        paint.pathEffect = null
        canvas.drawLine(
            centerX,
            centerY,
            centerX + (INDICATOR_LENGTH * (cos(Math.toRadians(ANGLE_START + (((360 - OPEN_ANGLE) * INDICATOR_INDEX) / MARK_COUNT).toDouble())))).toFloat()
            ,
            centerY + (INDICATOR_LENGTH * (sin(Math.toRadians(ANGLE_START + (((360 - OPEN_ANGLE) * INDICATOR_INDEX) / MARK_COUNT).toDouble())))).toFloat()
            , paint
        )
    }
}