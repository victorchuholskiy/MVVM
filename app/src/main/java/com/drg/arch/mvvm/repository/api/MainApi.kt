package com.drg.arch.mvvm.repository.api

import com.drg.arch.mvvm.data.entity.remote.common.BeerRemote
import com.drg.arch.mvvm.data.entity.remote.common.UserRemote
import com.drg.arch.mvvm.data.entity.remote.response.StatusResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by aleksey.stepanov
 * 4/26/18.
 */
interface MainApi {

	@GET("/users")
	fun loadUserList(): Single<List<UserRemote>>

	@GET("/users/{id}")
	fun loadUser(@Path("id") id: Int): Single<UserRemote>

	@POST("/users")
	fun addUser(@Body user: UserRemote): Single<StatusResponse>

	@GET("/beer")
	fun loadBeerList(): Single<List<BeerRemote>>
}