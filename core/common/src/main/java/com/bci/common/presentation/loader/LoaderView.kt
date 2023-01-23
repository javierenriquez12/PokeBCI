package com.bci.common.presentation.loader

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bci.common.databinding.ViewBciLoaderBinding

class LoaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    companion object {
        const val propertyNameAnimator = "rotation"
        const val startValueAnimator = 360f
        const val endValueAnimator = 0f
        const val durationAnimate = 1000L
    }

    private val binding: ViewBciLoaderBinding =
        ViewBciLoaderBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val rotate = ObjectAnimator.ofFloat(
            binding.loaderPokeballImageView,
            propertyNameAnimator,
            startValueAnimator,
            endValueAnimator
        )
        rotate.repeatCount = ValueAnimator.INFINITE
        rotate.duration = durationAnimate
        rotate.start()
    }
}