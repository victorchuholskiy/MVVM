package com.drg.arch.mvvm.data.entity.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created by aleksey.stepanov
 * 4/27/18.
 */
class StatusResponse(@SerializedName("status") val status: String)