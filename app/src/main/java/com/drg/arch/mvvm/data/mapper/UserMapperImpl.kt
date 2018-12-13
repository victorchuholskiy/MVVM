package com.drg.arch.mvvm.data.mapper

import com.drg.arch.mvvm.data.entity.db.UserDB
import com.drg.arch.mvvm.data.entity.remote.common.UserRemote

/**
 * Created by aleksey.stepanov
 * 4/23/18.
 */
object UserMapperImpl : UserMapper {

	override fun map(remote: UserRemote): UserDB {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun map(app: UserDB): UserRemote {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun map(remote: List<UserRemote>): List<UserDB> {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	//	override fun map(remote: UserRemote): UserDB = UserDB(
//			remote.id,
//			remote.name ?: "",
//			remote.age ?: 0)
//
//	override fun map(app: UserDB): UserRemote {
//		return UserRemote(app.id, app.name, app.age)
//	}
//
//	override fun map(remote: List<UserRemote>): List<UserDB> = mutableListOf<UserDB>().apply {
//		remote.forEach { add(map(it)) }
//	}
}