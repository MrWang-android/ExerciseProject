package com.example.myapplication

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator

import kotlinx.android.synthetic.main.activity_custom_view.*

class CustomViewActivity : AppCompatActivity() {
    var ValueAnimator: ValueAnimator?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)
        initview()
    }

    private fun initview() {
        qqstepview.maxstep = 6666
        ValueAnimator = ObjectAnimator.ofInt(0, 3000)
        ValueAnimator?.setDuration(1000)
        ValueAnimator?.setInterpolator(DecelerateInterpolator())
        ValueAnimator?.addUpdateListener(object :ValueAnimator.AnimatorUpdateListener{
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                val animatedValue = animation?.getAnimatedValue();
                qqstepview.creenstepint = animatedValue as Int
            }
        })
        ValueAnimator?.start()
    }
}

