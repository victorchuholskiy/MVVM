package com.drg.arch.mvvm.data.entity.remote.common

import com.google.gson.annotations.SerializedName

/**
 * Created by aleksey.stepanov
 * 4/23/18.
 */
data class UserRemote(
		@SerializedName("id") val id: Int,
		@SerializedName("name") val name: String?,
		@SerializedName("age") val age: Int?
)