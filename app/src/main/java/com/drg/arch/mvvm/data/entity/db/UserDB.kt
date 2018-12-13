package com.drg.arch.mvvm.data.entity.db

/**
 * Created by aleksey.stepanov
 * 4/26/18.
 */
//@Table(database = AppDataBase::class)
//data class UserDB(
//		@PrimaryKey(autoincrement = true) var id: Int = 0,
//		@Column var name: String = "def",
//		@Column var age: Int = -1
//) : BaseRXModel()

data class UserDB(
		var name: String = "def",
		var age: Int = -1
)