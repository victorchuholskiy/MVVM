package com.drg.arch.mvvm.repository.nodes.user

import com.drg.arch.mvvm.data.entity.remote.common.UserRemote
import io.reactivex.Single

/**
 * Created by aleksey.stepanov
 * 5/4/18.
 */

interface UserRemoteRepository {
	fun loadUserList(): Single<List<UserRemote>>
}