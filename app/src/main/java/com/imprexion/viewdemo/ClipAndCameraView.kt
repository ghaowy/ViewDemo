package com.imprexion.viewdemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave

/**
 * @author : gongh
 * @date   : 2020/5/19 15:29
 * @desc   : TODO
 */

class ClipAndCameraView(context: Context, attributeSet: AttributeSet) :
    View(context, attributeSet) {
    private val bitmap = getAvatarBitmap()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {

    }
    var topFlip = 0f
        set(value) {
            field = value
            invalidate()
        }
    var bottomFlip = 0f
        set(value) {
            field = value
            invalidate()
        }
    var rotateDegree = 0f
        set(value) {
            field = value
            invalidate()
        }

    private val camera = Camera()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.withSave {
            translate(bitmap.width / 2f, bitmap.height / 2f)
            rotate(-rotateDegree)
            camera.save()
            camera.rotateY(topFlip)
            camera.applyToCanvas(canvas)
            camera.restore()
            clipRect(
                -bitmap.width.toFloat(),
                -bitmap.height.toFloat(),
                0f,
                bitmap.height.toFloat()
            )
            rotate(rotateDegree)
            translate(-bitmap.width / 2f, -bitmap.height / 2f)
            drawBitmap(bitmap, 0f, 0f, paint)
        }

        canvas.withSave {
            translate(bitmap.width / 2f, bitmap.height / 2f)
            rotate(-rotateDegree)
            camera.save()
            camera.rotateY(bottomFlip)
            camera.applyToCanvas(canvas)
            camera.restore()
            clipRect(
                0f,
                -bitmap.height.toFloat(),
                bitmap.width.toFloat(),
                bitmap.height.toFloat()
            )
            rotate(rotateDegree)
            translate(-bitmap.width / 2f, -bitmap.height / 2f)
            drawBitmap(bitmap, 0f, 0f, paint)
        }


    }

    private fun getAvatarBitmap(): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = false
        val decodeResource =
            BitmapFactory.decodeResource(resources, R.mipmap.dialog_img_error, options)
        val copyBitmap =
            Bitmap.createBitmap(decodeResource.width, decodeResource.height, decodeResource.config)
        val canvas = Canvas()
        canvas.setBitmap(copyBitmap)
        canvas.drawColor(Color.GREEN)
        canvas.drawBitmap(decodeResource, 0f, 0f, paint)

        return copyBitmap
    }
}