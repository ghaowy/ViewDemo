package com.imprexion.viewdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.imprexion.viewdemo.activity.ViewSizeDemoActivity
import com.imprexion.viewdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainBinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)


        mainBinding.btnViewSize.setOnClickListener {
            startActivity(Intent(this, ViewSizeDemoActivity::class.java));
        }


//        val animator = ObjectAnimator.ofFloat(mainBinding.animView, "radious", 300f.px)
//        animator.interpolator = AccelerateDecelerateInterpolator()
//        animator.startDelay = 1500
//        animator.duration = 3000
//        animator.start()

//        val animator = ObjectAnimator.ofObject(
//            mainBinding.animView,
//            "point",
//            AnimPropertyView.PointEnvaluator(),
//            PointF(400f.px, 600f.px)
//        )
//        animator.startDelay = 1500
//        animator.duration = 2000
//        animator.start()

//        val topFlipAnimator = ObjectAnimator.ofFloat(mainBinding.animView, "topFlip", 45f)
//        topFlipAnimator.duration = 1500
//        val bottomFlipAnimator = ObjectAnimator.ofFloat(mainBinding.animView, "bottomFlip", 45f)
//        bottomFlipAnimator.duration = 1500
//        val rotateAnimator = ObjectAnimator.ofFloat(mainBinding.animView, "rotateDegree", 360f)
//        rotateAnimator.duration = 2000
//
//
//        val animatorSet = AnimatorSet()
//        animatorSet.playTogether(topFlipAnimator, bottomFlipAnimator, rotateAnimator)
//        animatorSet.startDelay = 1500
//        animatorSet.start()


//        val keyframe = Keyframe.ofFloat(0.2f, 0.4f)
//        val keyframe1 = Keyframe.ofFloat(0.8f, 0.6f)
//        val keyframe2 = Keyframe.ofFloat(1f, 1.0f)
//
//        val topFlipAnimator = PropertyValuesHolder.ofFloat("topFlip", 45f)
//        topFlipAnimator.setKeyframes(keyframe, keyframe1, keyframe2)
//        val bottomFlipAnimator = PropertyValuesHolder.ofFloat("bottomFlip", 45f)
//        val rotateAnimator = PropertyValuesHolder.ofFloat("rotateDegree", 45f)
//        val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
//            mainBinding.animView,
//            topFlipAnimator,
//            bottomFlipAnimator,
//            rotateAnimator
//        )
//        objectAnimator.startDelay = 1500
//        objectAnimator.duration = 1500
//        objectAnimator.start()


    }
}
