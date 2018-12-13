package com.drg.arch.mvvm.usecase.user

import com.drg.arch.mvvm.data.entity.remote.common.UserRemote
import io.reactivex.Single

/**
 * Created by aleksey.stepanov
 * 8/13/18.
 */
interface LoadUserListUseCase {

	fun loadUserList(): Single<List<UserRemote>>
}