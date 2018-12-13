package com.drg.arch.mvvm.data.entity.remote.common

import com.google.gson.annotations.SerializedName

data class BeerRemote(@SerializedName("brewery") val brewery: String,
					  @SerializedName("name") val name: String,
					  @SerializedName("alc") val alc: Float,
					  @SerializedName("ibu") val ibu: Int,
					  @SerializedName("plate") val plate: Int)