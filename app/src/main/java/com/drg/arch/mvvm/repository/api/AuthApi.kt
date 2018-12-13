package com.drg.arch.mvvm.repository.api

import com.drg.arch.mvvm.data.entity.remote.response.StatusResponse
import io.reactivex.Single
import retrofit2.http.POST

/**
 * Created by aleksey.stepanov
 * 5/16/18.
 */
interface AuthApi {

	@POST("/login")
	fun login(): Single<StatusResponse>
}