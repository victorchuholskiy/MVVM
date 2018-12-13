package com.drg.arch.mvvm.repository

import android.content.Context
import android.content.SharedPreferences
import com.nalulabs.prefs.BuildConfig
import com.nalulabs.prefs.string

/**
 * Created by aleksey.stepanov
 * 4/24/18.
 */

private const val PREF_NAME: String = "mainPrefs"

class Prefs(context: Context) {

	private val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

	var accessToken by prefs.string()
	var appVersion by prefs.string(BuildConfig.VERSION_NAME)

}