package com.drg.arch.mvvm.repository.nodes.user

import com.drg.arch.mvvm.data.entity.db.UserDB
import io.reactivex.Single

/**
 * Created by aleksey.stepanov
 * 5/4/18.
 */
class UserLocalRepositoryImpl : UserLocalRepository {

	override fun getUserList(): Single<MutableList<UserDB>> {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun saveUser(user: UserDB): Single<Boolean> {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun saveUserList(userList: List<UserDB>) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
}