package com.drg.arch.mvvm.data.entity.remote.common

import com.google.gson.annotations.SerializedName

data class MapRemote(@SerializedName("id") val id: String,
					 @SerializedName("name") val name: String,
					 @SerializedName("lat") val lat: Double,
					 @SerializedName("lon") val lon: Double)