package com.bci.uicomponents.utils

import android.view.View

internal fun View.isVisible(isVisible: Boolean) {
    if (isVisible) visibility = View.VISIBLE else View.GONE
}