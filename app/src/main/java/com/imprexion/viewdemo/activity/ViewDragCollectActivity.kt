package com.imprexion.viewdemo.activity

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.imprexion.viewdemo.R
import com.imprexion.viewdemo.databinding.ActivityDragUpDownBinding

/**
 * @author : gongh
 * @date   : 2020/7/8 17:34
 * @desc   : TODO
 */
class ViewDragCollectActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val dataBindingUtil = DataBindingUtil.setContentView<ActivityDragUpDownBinding>(
            this,
            R.layout.activity_drag_up_down
        )

//        dataBindingUtil.tvStudy.setOnLongClickListener {
//            it.startDragAndDrop(
//                ClipData.newPlainText("name", tv_study.text),
//                View.DragShadowBuilder(it),
//                it,
//                0
//            )
//        }
//
//        dataBindingUtil.ivFood.setOnLongClickListener {
//            it.startDragAndDrop(
//                ClipData.newPlainText("name", it.contentDescription),
//                View.DragShadowBuilder(it),
//                it,
//                0
//            )
//        }
//
//        dataBindingUtil.llResult.setOnDragListener(object : View.OnDragListener {
//            override fun onDrag(v: View, event: DragEvent): Boolean {
//                when (event.action) {
//                    DragEvent.ACTION_DROP -> {
//                        if (v.id == R.id.ll_result) {
//                            val textView = TextView(this@ViewDragCollectActivity)
//                            textView.setText(event.clipData.getItemAt(0).text)
//                            dataBindingUtil.llResult.addView(textView)
//                        }
//                    }
//                }
//                return true
//            }
//        })
    }
}