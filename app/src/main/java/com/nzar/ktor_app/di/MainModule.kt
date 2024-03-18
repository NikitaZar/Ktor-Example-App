package com.nzar.ktor_app.di

import com.nzar.ktor_app.mappers.SpPositionToMethodMapper
import com.nzar.ktor_app.mappers.SpPositionToMethodMapperImpl
import com.nzar.ktor_app.mappers.TextToPairQueryMapper
import com.nzar.ktor_app.mappers.TextToPairQueryMapperImpl
import com.nzar.ktor_app.repository.NetRepository
import com.nzar.ktor_app.repository.NetRepositoryImpl
import com.nzar.ktor_app.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    viewModel {
        MainViewModel(
            netRepository = get(),
            toMethodMapper = get(),
            toPairQueryMapper = get(),
        )
    }

    factory<SpPositionToMethodMapper> {
        SpPositionToMethodMapperImpl()
    }

    factory<TextToPairQueryMapper> {
        TextToPairQueryMapperImpl()
    }

    single<NetRepository> {
        NetRepositoryImpl(api = get())
    }
}