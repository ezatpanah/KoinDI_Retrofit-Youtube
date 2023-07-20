package com.ezatpanah.koindi_retrofit_youtube.api

import com.ezatpanah.koindi_retrofit_youtube.response.PhotoResponse
import com.ezatpanah.koindi_retrofit_youtube.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiServices {

    @GET("api/")
    suspend fun getPhoto(
        @Query("q") searchQuery: String,
        @Query("image_type") imageType: String = "photo",
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ): Response<PhotoResponse>

}