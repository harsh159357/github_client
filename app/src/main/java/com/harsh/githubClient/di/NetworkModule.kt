package com.harsh.githubClient.di

import com.harsh.githubClient.data.api.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class TokenInterceptor : Interceptor {

    companion object {
        const val personalAccessToken = "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"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequest: Request = chain.request().newBuilder()
            .header("Authorization", "Bearer $personalAccessToken")
            .build()
        return chain.proceed(newRequest)
    }
}

val tokenInterceptor = TokenInterceptor()

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideApiService(get(), ApiService::class.java) }
}

fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder().addInterceptor(tokenInterceptor).build()

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()

fun provideApiService(retrofit: Retrofit, apiService: Class<ApiService>) =
    createService(retrofit, apiService)

fun <T> createService(retrofit: Retrofit, serviceClass: Class<T>): T = retrofit.create(serviceClass)