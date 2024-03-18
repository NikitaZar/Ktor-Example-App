package com.nzar.ktor_app.di

import com.nzar.ktor_app.ktorUtils.KtorUtils
import com.nzar.ktor_app.ktorUtils.KtorUtilsImpl
import com.nzar.ktor_app.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    viewModel {
        MainViewModel(
            netRepository = get(),
            toMethodMapper = get(),
        )
    }
    factory<KtorUtils> {
        KtorUtilsImpl(
            client = get()
        )
    }
}