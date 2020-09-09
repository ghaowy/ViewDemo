package com.imprexion.viewdemo.animation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.imprexion.viewdemo.R
import com.imprexion.viewdemo.databinding.ActivityAnimMotionBinding

/**
 * @author : gongh
 * @date   : 2020/7/16 11:40
 * @desc   : TODO
 */
class MotionAnimActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding = DataBindingUtil.setContentView<ActivityAnimMotionBinding>(
            this,
            R.layout.activity_anim_motion
        )

        dataBinding.btnText.setOnClickListener {
            startActivity(Intent(this, TextAnimActivity::class.java))
        }
        dataBinding.btnScroll.setOnClickListener {
            startActivity(Intent(this, ScrollAnimActivity::class.java))
        }
        dataBinding.btnCat.setOnClickListener {
            startActivity(Intent(this, CatAnimActivity::class.java))
        }
    }
}