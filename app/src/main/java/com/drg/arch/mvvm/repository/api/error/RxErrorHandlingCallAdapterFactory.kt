package com.drg.arch.mvvm.repository.api.error

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import java.lang.reflect.Type

class RxErrorHandlingCallAdapterFactory private constructor() : CallAdapter.Factory() {

	private val original: RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

	companion object {
		fun create(): CallAdapter.Factory {
			return RxErrorHandlingCallAdapterFactory()
		}
	}

	override fun get(returnType: Type,
					 annotations: Array<Annotation>,
					 retrofit: Retrofit): CallAdapter<Any, Any> {
		@Suppress("UNCHECKED_CAST")
		return RxCallAdapterWrapper(retrofit, original.get(returnType, annotations, retrofit) as CallAdapter<Any, Any>)
	}

	/* data types */

	private class RxCallAdapterWrapper(private val retrofit: Retrofit,
									   private val wrapped: CallAdapter<Any, Any>) : CallAdapter<Any, Any> {

		override fun responseType(): Type {
			return wrapped.responseType()
		}

		override fun adapt(call: Call<Any>): Any {
			val result = wrapped.adapt(call)
			if (result is Single<*>) {
				return result.onErrorResumeNext { throwable -> Single.error(asRetrofitException(throwable)) }
			}
			if (result is Observable<*>) {
				return result.onErrorResumeNext { throwable: Throwable -> Observable.error(asRetrofitException(throwable)) }
			}

			return if (result is Completable) {
				result.onErrorResumeNext { throwable -> Completable.error(asRetrofitException(throwable)) }
			} else result

		}

		private fun asRetrofitException(throwable: Throwable): RetrofitException {
			// We had non-200 http error
			if (throwable is HttpException) {
				val response = throwable.response()
				return RetrofitException.httpError(throwable, response.raw().request().url().toString(), response, retrofit)
			}
			// A network error happened
			return if (throwable is IOException) {
				RetrofitException.networkError(throwable)
				// We don't know what happened. We need to simply convert to an unknown error
			} else RetrofitException.unexpectedError(throwable)

		}
	}
}