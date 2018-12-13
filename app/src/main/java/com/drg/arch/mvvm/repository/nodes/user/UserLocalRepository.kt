package com.drg.arch.mvvm.repository.nodes.user

import com.drg.arch.mvvm.data.entity.db.UserDB
import io.reactivex.Single

/**
 * Created by aleksey.stepanov
 * 5/4/18.
 */
interface UserLocalRepository {

	fun getUserList(): Single<MutableList<UserDB>>

	fun saveUser(user: UserDB): Single<Boolean>

	fun saveUserList(userList: List<UserDB>)
}