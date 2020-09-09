package com.imprexion.viewdemo.animation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.imprexion.viewdemo.R
import com.imprexion.viewdemo.databinding.ActivityAnimCatBinding

/**
 * @author : gongh
 * @date   : 2020/7/16 15:01
 * @desc   : TODO
 */

class CatAnimActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val dataBindingUtil =
//            DataBindingUtil.setContentView<ActivityAnimCatBinding>(this, R.layout.activity_anim_cat)

        setContentView(R.layout.activity_anim_cat)
    }

}