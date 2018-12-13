package com.drg.arch.mvvm.extension

import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * Created by aleksey.stepanov
 * 4/28/18.
 */

fun Fragment.toast(msg: String?,
				   length: Int = Toast.LENGTH_SHORT) = Toast.makeText(context, msg, length).show()

fun Fragment.toast(@StringRes msgRes: Int,
				   length: Int = Toast.LENGTH_SHORT) = Toast.makeText(context, msgRes, length).show()