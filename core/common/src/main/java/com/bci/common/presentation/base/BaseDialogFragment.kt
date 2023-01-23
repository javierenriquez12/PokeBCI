package com.bci.common.presentation.base

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bci.common.presentation.loader.LoaderView
import com.bci.common.presentation.fitLayoutMatchParentParams
import com.bci.common.presentation.isVisible

abstract class BaseDialogFragment : DialogFragment() {

    private lateinit var loaderView: View

    fun addLoader(){
        loaderView  = LoaderView(requireContext()).apply {
            fitLayoutMatchParentParams()
        }
        val container =view?.parent as? ViewGroup
        container?.addView(
            loaderView
        )
    }

    fun showOrHideLoad(container: View,isLoad: Boolean) {
        if(isLoad) {
            container.isVisible(false)
            loaderView.isVisible(true)
        } else {
            container.isVisible(true)
            loaderView.isVisible(false)
        }
    }
}