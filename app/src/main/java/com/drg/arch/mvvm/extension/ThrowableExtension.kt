package com.drg.arch.mvvm.extension

import com.drg.arch.mvvm.data.entity.remote.response.error.SimpleErrorResponse
import com.drg.arch.mvvm.repository.api.error.RetrofitException

/**
 * Created by aleksey.stepanov
 * 5/7/18.
 */
fun Throwable.processError(): String? = when (this) {
	is RetrofitException -> when (kind) {
		RetrofitException.Kind.HTTP -> try {
			getErrorBodyAs(SimpleErrorResponse::class.java)?.msg
		} catch (e: Exception) {
			e.toString()
		}
		RetrofitException.Kind.NETWORK -> "No internet connection"
		RetrofitException.Kind.UNEXPECTED -> "UNEXPECTED error"
	}
	else -> "Not Retrofit error: ${toString()}"
}
//fun Throwable.processError(clazz: Class<out Any> = SimpleErrorResponse::class.java): Any = try {
//	when (this) {
//		is RetrofitException -> when(kind) {
//			RetrofitException.Kind.HTTP -> "s"
//			RetrofitException.Kind.NETWORK -> "No internet connection"
//			RetrofitException.Kind.UNEXPECTED -> "UNEXPECTED error"
////			getErrorBodyAs(clazz)?.msg
//		}
//		else -> "Not Retrofit error: ${toString()}"
//	}
//} catch (e: Exception) {
//	e.toString()
//}
//fun <T> Throwable.processError(clazz: Class<T>): T? =
//		when (this) {
//			is RetrofitException -> getErrorBodyAs(clazz)
//			else -> null
//		}

//fun  Throwable.processError(): String? =
//		when (this) {
//			is RetrofitException -> getErrorBodyAs(clazz)
//			else -> null
//		}