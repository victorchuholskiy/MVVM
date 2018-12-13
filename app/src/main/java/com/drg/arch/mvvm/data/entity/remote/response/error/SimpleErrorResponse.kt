package com.drg.arch.mvvm.data.entity.remote.response.error

import com.google.gson.annotations.SerializedName

/**
 * Created by aleksey.stepanov
 * 5/7/18.
 */
data class SimpleErrorResponse(
		@SerializedName("status") val status: String,
		@SerializedName("message") val msg: String?
)