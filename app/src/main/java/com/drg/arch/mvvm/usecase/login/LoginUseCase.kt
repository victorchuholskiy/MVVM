package com.drg.arch.mvvm.usecase.login

import com.drg.arch.mvvm.data.entity.remote.response.StatusResponse
import io.reactivex.Single

/**
 * Created by aleksey.stepanov
 * 8/15/18.
 */
interface LoginUseCase {

	fun login(): Single<StatusResponse>
}