package com.ezatpanah.koindi_retrofit_youtube.di

import com.ezatpanah.koindi_retrofit_youtube.repository.ApiRepository
import com.ezatpanah.koindi_retrofit_youtube.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val photoModule = module {
    factory { ApiRepository(get()) }
    viewModel { MainViewModel(get()) }
}