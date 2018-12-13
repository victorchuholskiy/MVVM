package com.drg.arch.mvvm.repository.api.error

import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

class RetrofitException private constructor(
		exception: Throwable,
		message: String?,
		val url: String?,
		val response: Response<*>?,
		val kind: Kind,
		val retrofit: Retrofit?
) : RuntimeException(message, exception) {

	companion object {

		fun httpError(exception: Throwable,
					  url: String,
					  response: Response<*>,
					  retrofit: Retrofit): RetrofitException {
			val message = response.code().toString() + " " + response.message()
			return RetrofitException(exception, message, url, response, Kind.HTTP, retrofit)
		}

		fun networkError(exception: IOException): RetrofitException {
			return RetrofitException(exception, exception.message, null, null, Kind.NETWORK, null)
		}

		fun unexpectedError(exception: Throwable): RetrofitException {
			return RetrofitException(exception, exception.message, null, null, Kind.UNEXPECTED, null)
		}
	}

	fun <T> getErrorBodyAs(type: Class<T>): T? {
		if (retrofit != null && response != null && response.errorBody() != null) {
			val converter = retrofit.responseBodyConverter<T>(type, arrayOfNulls(0))
			return converter.convert(response.errorBody()!!)
		}
		return null
	}

	/* data types */

	enum class Kind {
		NETWORK,
		HTTP,
		UNEXPECTED
	}
}
