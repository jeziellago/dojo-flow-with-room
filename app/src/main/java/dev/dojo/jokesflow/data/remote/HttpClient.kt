package dev.dojo.jokesflow

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass
import dev.dojo.jokesflow.BuildConfig

fun createHttpClient(): HttpClient = HttpClientImpl(retrofit = createRetrofit())

interface HttpClient {
    fun <T : Any> create(service: KClass<T>): T
}

internal class HttpClientImpl(private val retrofit: Retrofit) : HttpClient {
    override fun <T : Any> create(service: KClass<T>): T {
        return retrofit.create(service.java)
    }
}

fun createRetrofit(
    baseUrl: String = "https://api.chucknorris.io/",
    client: OkHttpClient = createOkHttpClient()
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun createOkHttpClient(): OkHttpClient {
    val logLevel = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
    } else {
        HttpLoggingInterceptor.Level.NONE
    }
    return OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor().apply { level = logLevel })
        .build()
}