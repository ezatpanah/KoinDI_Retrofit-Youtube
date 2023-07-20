package com.ezatpanah.koindi_retrofit_youtube.repository

import com.ezatpanah.koindi_retrofit_youtube.api.ApiServices
import com.ezatpanah.koindi_retrofit_youtube.utils.DataStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ApiRepository(
    private val apiServices: ApiServices,
) {
    suspend fun getPhoto(strSearchPhoto: String) = flow {
        emit(DataStatus.loading())
        val result = apiServices.getPhoto(strSearchPhoto)
        when (result.code()) {
            200 -> emit(DataStatus.success(result.body()?.hits))
            400 -> emit(DataStatus.error(result.message()))
            500 -> emit(DataStatus.error(result.message()))
        }
    }
        .catch {
            emit(DataStatus.error(it.message.toString()))
        }
        .flowOn(Dispatchers.IO)
}