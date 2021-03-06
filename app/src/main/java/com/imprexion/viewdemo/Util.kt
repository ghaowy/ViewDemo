package com.imprexion.viewdemo

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory

/**
 * @author : gongh
 * @date   : 2020/6/28 17:50
 * @desc   : TODO
 */
object Util {
    fun getImageBitmap(res: Resources, width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(res, R.mipmap.pic, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(res, R.mipmap.pic, options)

    }
}