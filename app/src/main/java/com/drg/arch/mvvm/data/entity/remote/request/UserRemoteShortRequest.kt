package com.drg.arch.mvvm.data.entity.remote.request

import com.google.gson.annotations.SerializedName

/**
 * Created by aleksey.stepanov
 * 4/23/18.
 */
data class UserRemoteShortRequest(
		@SerializedName("id") val id: Int,
		@SerializedName("extra_info") val extra_info: String?
)