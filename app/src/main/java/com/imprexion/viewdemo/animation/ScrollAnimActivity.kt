package com.imprexion.viewdemo.animation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.imprexion.viewdemo.R
import com.imprexion.viewdemo.databinding.ActivityAnimScrollBinding

/**
 * @author : gongh
 * @date   : 2020/7/16 14:19
 * @desc   : TODO
 */
class ScrollAnimActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding = DataBindingUtil.setContentView<ActivityAnimScrollBinding>(
            this,
            R.layout.activity_anim_scroll
        )
    }
}