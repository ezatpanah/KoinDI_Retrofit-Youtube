package com.ezatpanah.koindi_retrofit_youtube.di


import com.ezatpanah.koindi_retrofit_youtube.api.ApiServices
import com.ezatpanah.koindi_retrofit_youtube.utils.Constants.BASE_URL
import com.ezatpanah.koindi_retrofit_youtube.utils.Constants.NETWORK_TIMEOUT
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val baseUrl = BASE_URL

const val networkTime = NETWORK_TIMEOUT

fun provideGson(): Gson = GsonBuilder().setLenient().create()

fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    val requestInterceptor = Interceptor { chain ->
        val url = chain.request()
            .url
            .newBuilder()
            .build()

        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        return@Interceptor chain.proceed(request)
    }

    OkHttpClient
        .Builder()
        .addInterceptor(requestInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()
} else {
    OkHttpClient
        .Builder()
        .build()
}


fun provideRetrofit(baseUrl: String, gson: Gson, client: OkHttpClient): ApiServices =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(ApiServices::class.java)


val apiModule = module {

    single { baseUrl }
    single { networkTime }
    single { provideGson() }
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), get(), get()) }

}