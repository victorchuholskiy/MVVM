package com.drg.arch.mvvm.data.mapper

import com.drg.arch.mvvm.data.entity.db.UserDB
import com.drg.arch.mvvm.data.entity.remote.common.UserRemote

/**
 * Created by aleksey.stepanov
 * 5/11/18.
 */
interface UserMapper {

	fun map(remote: UserRemote): UserDB

	fun map(app: UserDB): UserRemote

	fun map(remote: List<UserRemote>): List<UserDB>
}