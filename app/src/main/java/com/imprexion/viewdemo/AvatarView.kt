package com.imprexion.viewdemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


/**
 * @author : gongh
 * @date   : 2020/5/14 13:52
 * @desc   : TODO
 */
val PADDING_TOP_AVATAR = 100f.px
val PADDING_START_AVATAR = 100f.px
val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)


class AvatarView : View {
    private val bitmap = getAvatarBitmap(300f.px.toInt(), 300f.px.toInt())
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#22DDDD")
    }
    private val bound = RectF(
        PADDING_START_AVATAR,
        PADDING_TOP_AVATAR,
        PADDING_START_AVATAR + bitmap.width,
        PADDING_TOP_AVATAR + bitmap.height
    )


    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val saveLayer = canvas.saveLayer(bound, null)
        canvas.drawCircle(
            PADDING_START_AVATAR + (bitmap.width / 2),
            PADDING_TOP_AVATAR + (bitmap.height / 2),
            (bitmap.width / 2).toFloat(),
            paint
        )
        paint.xfermode = xfermode
        canvas.drawBitmap(bitmap, PADDING_START_AVATAR, PADDING_TOP_AVATAR, paint)
        canvas.restoreToCount(saveLayer)
    }


    private fun getAvatarBitmap(reqWidth: Int, reqHeight: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.mipmap.dialog_img_error, options)
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2
            while (halfHeight / inSampleSize > reqHeight && halfWidth / inSampleSize > reqWidth) {
                inSampleSize *= 2
            }
        }
        options.inJustDecodeBounds = false
        options.inSampleSize = inSampleSize
        return BitmapFactory.decodeResource(resources, R.mipmap.dialog_img_error, options)
    }

}