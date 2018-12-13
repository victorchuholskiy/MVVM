package com.drg.arch.mvvm.util.crypt

import android.os.Build

/**
 * Created by aleksey.stepanov
 * 10/4/18
 */

class SystemServices {

	companion object {
		fun hasMarshmallow() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
	}
}