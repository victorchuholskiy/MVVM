package com.drg.arch.mvvm.bindAdapters

import android.databinding.BindingAdapter
import android.support.design.widget.TextInputLayout

/**
 * Created by aleksey.stepanov
 * 8/9/18.
 */
@BindingAdapter("android:error")
fun TextInputLayout.error(error: String?) {
	this.error = error
}