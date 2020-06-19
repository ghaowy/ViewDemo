package com.imprexion.viewdemo

import android.content.Context
import android.graphics.*
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * @author : gongh
 * @date   : 2020/5/18 15:22
 * @desc   : TODO
 */

var RADIOUS = 200f.px
var ROUND_WIDTH = 20f.px
val VERTICAL_PADDING = 130f.px
const val TEXT =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis euismod diam eu justo condimentum, sit amet commodo arcu rutrum. Donec iaculis, justo non luctus scelerisque, purus erat cursus nulla, at tempus justo dui facilisis nisi. Aliquam vitae viverra massa. Pellentesque tempor scelerisque est, vel scelerisque metus pulvinar vel. Nullam sed feugiat enim. Phasellus ultrices sagittis ex, eget laoreet mi. Sed id eros erat. Fusce pulvinar ex aliquam nunc egestas, id sagittis lectus maximus. Sed semper tempus elit, id sollicitudin dolor ultrices in. Maecenas efficitur dignissim massa ut aliquam."
const val TAG = "TextDrawView"

var WIDTH = 367f / 2
var HEIGHT = 445f / 2

class TextDrawView : View {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#2BD5D5")
        style = Paint.Style.STROKE
        strokeWidth = ROUND_WIDTH
        strokeCap = Paint.Cap.ROUND
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#2BD5D5")
        style = Paint.Style.FILL
        textSize = 100f.px
        textAlign = Paint.Align.LEFT
    }

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#FFFF00")
        style = Paint.Style.STROKE
        strokeWidth = 5f.px
    }

    private val lineTextPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#2BD5D5")
        style = Paint.Style.FILL
        textAlign = Paint.Align.LEFT
        textSize = 32f.px
    }

    private val bitmap = getAvatarBitmap()

    private val textBound = Rect()
    private val fontMetrics = Paint.FontMetrics()
    private val floatArray = floatArrayOf()

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        canvas.drawCircle(width / 2f, height / 2f, RADIUS, paint)


//        Log.d(TAG, "onDraw(): bitmap.width= ${bitmap.width}  bitmap.height= ${bitmap.height}")
        canvas.drawBitmap(
            bitmap,
            width.toFloat() - WIDTH,
            VERTICAL_PADDING,
            paint
        )
        lineTextPaint.getFontMetrics(fontMetrics)
        var startIndex = 0
        var count = 0
        var verticalOffset = -fontMetrics.top
        var maxWidth = 0

        Log.d(TAG, "onDraw(): fontMetrics= ${fontMetrics.string()}")
        while (startIndex < TEXT.length) {
            maxWidth =
                if ((verticalOffset + fontMetrics.bottom) < VERTICAL_PADDING || verticalOffset + fontMetrics.top > HEIGHT + VERTICAL_PADDING) {
                    width
                } else {
                    (width.toFloat() - WIDTH).toInt()
                }

            count =
                lineTextPaint.breakText(
                    TEXT,
                    startIndex,
                    TEXT.length,
                    true,
                    maxWidth.toFloat(),
                    floatArray
                )
            canvas.drawText(TEXT, startIndex, startIndex + count, 0f, verticalOffset, lineTextPaint)
            canvas.drawLine(
                0f, verticalOffset,
                width.toFloat(), verticalOffset, linePaint
            )
            startIndex += count
            verticalOffset += lineTextPaint.fontSpacing
        }

        // 绘制Text的边距 对齐效果
//        drawTextBound(canvas)

//        noBitmapDrawLine(canvas)


    }

    private fun noBitmapDrawLine(canvas: Canvas) {
        // 绘制多行文字
        val staticLayout =
            StaticLayout(TEXT, lineTextPaint, width, Layout.Alignment.ALIGN_NORMAL, 1F, 0F, false)
        staticLayout.draw(canvas)


    }

    val func = fun(a: Int, b: Int) = a + b


    private fun drawTextBound(canvas: Canvas) {
        paint.color = Color.parseColor("#FF00FF")
        canvas.drawArc(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2f + RADIUS,
            height / 2f + RADIUS,
            -90F,
            120f,
            false,
            paint
        )

        textPaint.textAlign = Paint.Align.LEFT
        textPaint.getFontMetrics(fontMetrics)
        textPaint.getTextBounds(TEXT, 0, TEXT.length, textBound)
        Log.d(TAG, "onDraw(): textBound $textBound")
        Log.d(TAG, "onDraw(): fontMetrics ${fontMetrics.string()}")
        canvas.drawText(TEXT, 0f, -textBound.top.toFloat(), textPaint)
        canvas.drawText(
            TEXT,
            (-textBound.left).toFloat(),
            -textBound.top.toFloat() + paint.fontSpacing,
            textPaint
        )
    }

    private fun getAvatarBitmap(): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeResource(resources, R.mipmap.dialog_img_error, options)
    }
}