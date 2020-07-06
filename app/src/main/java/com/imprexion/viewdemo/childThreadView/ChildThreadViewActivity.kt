package com.imprexion.viewdemo.childThreadView

import android.app.Activity
import android.os.Bundle
import android.os.SystemClock
import androidx.databinding.DataBindingUtil
import com.imprexion.viewdemo.R
import com.imprexion.viewdemo.databinding.ActivityChildThreadBinding
import kotlin.concurrent.thread

/**
 * @author : gongh
 * @date   : 2020/6/22 15:49
 * @desc   : TODO
 */
class ChildThreadViewActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_child_thread)
        val dataBinding = DataBindingUtil.setContentView<ActivityChildThreadBinding>(
            this,
            R.layout.activity_child_thread
        )
        dataBinding.executePendingBindings()

//        dataBinding.btnClick.setOnClickListener {
//
//        }

        thread {
            SystemClock.sleep(2000)
            dataBinding.btnClick.text = "YI GAI"
        }


    }
}