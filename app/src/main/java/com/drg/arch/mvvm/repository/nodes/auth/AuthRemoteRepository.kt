package com.drg.arch.mvvm.repository.nodes.auth

import com.drg.arch.mvvm.data.entity.remote.response.StatusResponse
import io.reactivex.Single

/**
 * Created by aleksey.stepanov
 * 5/8/18.
 */
interface AuthRemoteRepository {
	fun login(): Single<StatusResponse>
}