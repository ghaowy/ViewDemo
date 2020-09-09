package com.imprexion.viewdemo.animation

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import com.imprexion.viewdemo.R

/**
 * @author : gongh
 * @date   : 2020/7/15 14:13
 * @desc   : TODO
 */
class TextAnimActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim_text)
        findViewById<Button>(R.id.debug).setOnClickListener {
            findViewById<MotionLayout>(R.id.motion_root).apply {
                setDebugMode(MotionLayout.DEBUG_SHOW_PATH)
            }
        }
    }
}