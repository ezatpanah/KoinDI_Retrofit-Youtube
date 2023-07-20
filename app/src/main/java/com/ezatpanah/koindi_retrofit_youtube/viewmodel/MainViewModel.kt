package com.ezatpanah.koindi_retrofit_youtube.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezatpanah.koindi_retrofit_youtube.repository.ApiRepository
import com.ezatpanah.koindi_retrofit_youtube.response.PhotoResponse
import com.ezatpanah.koindi_retrofit_youtube.utils.DataStatus
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ApiRepository) : ViewModel() {

    private val _photoList = MutableLiveData<DataStatus<List<PhotoResponse.Hit>>>()
    val photoList: LiveData<DataStatus<List<PhotoResponse.Hit>>>
        get() = _photoList


    fun getPhoto(strSearchPhoto: String) = viewModelScope.launch {
        repository.getPhoto(strSearchPhoto).collect {
            _photoList.value = it
        }
    }


}