package com.bci.common.presentation

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

fun View.isVisible(isVisible: Boolean) =
    if (isVisible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }


fun View.fitLayoutParams() = ViewGroup.LayoutParams(
    ViewGroup.LayoutParams.WRAP_CONTENT,
    ViewGroup.LayoutParams.WRAP_CONTENT
)

fun View.fitLayoutMatchParentParams() = ViewGroup.LayoutParams(
    ViewGroup.LayoutParams.MATCH_PARENT,
    ViewGroup.LayoutParams.MATCH_PARENT
)

fun Context.showMessage(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()