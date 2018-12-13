package com.drg.arch.mvvm.di.module

import com.drg.arch.mvvm.BuildConfig
import com.drg.arch.mvvm.di.scope.AppScope
import com.drg.arch.mvvm.repository.api.AuthApi
import com.drg.arch.mvvm.repository.api.MainApi
import com.drg.arch.mvvm.repository.api.MapApi
import com.drg.arch.mvvm.repository.api.error.RxErrorHandlingCallAdapterFactory
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

/**
 * Created by aleksey.stepanov
 * 8/15/18.
 */

@Module
object RetrofitModule {

	@JvmStatic
	@Provides
	@AppScope
	fun provideMainApi(@Named("mainApi") retrofit: Retrofit) = retrofit.create<MainApi>(MainApi::class.java)!!

	@JvmStatic
	@Provides
	@AppScope
	fun provideAuthApi(@Named("authApi") retrofit: Retrofit) = retrofit.create<AuthApi>(AuthApi::class.java)!!

	@JvmStatic
	@Provides
	@AppScope
	fun provideMapApi(@Named("mapApi") retrofit: Retrofit) = retrofit.create<MapApi>(MapApi::class.java)!!

	@JvmStatic
	@Provides
	@Named("mainApi")
	@AppScope
	fun provideRetrofitMain(okHttpClient: OkHttpClient,
							gson: Gson) =
			buildRetrofit(okHttpClient, gson, BuildConfig.MAIN_API_URL)

	@JvmStatic
	@Provides
	@Named("authApi")
	@AppScope
	fun provideRetrofitAuth(okHttpClient: OkHttpClient,
							gson: Gson) =
			buildRetrofit(okHttpClient, gson, BuildConfig.AUTH_API_URL)

	@JvmStatic
	@Provides
	@Named("mapApi")
	@AppScope
	fun provideRetrofitMap(okHttpClient: OkHttpClient,
						   gson: Gson) =
			buildRetrofit(okHttpClient, gson, BuildConfig.MAP_API_URL)

	@JvmStatic
	@Provides
	@AppScope
	fun provideOkHttpClient() = with(OkHttpClient.Builder()) {
		interceptors().add(HttpLoggingInterceptor().apply {
			level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else level

		})
		build()
	}!!

	@JvmStatic
	private fun buildRetrofit(
			okHttpClient: OkHttpClient,
			gson: Gson,
			url: String) =
			Retrofit.Builder()
					.client(okHttpClient)
					.baseUrl(url)
					.addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
					.addConverterFactory(GsonConverterFactory.create(gson))
					.build()!!
}