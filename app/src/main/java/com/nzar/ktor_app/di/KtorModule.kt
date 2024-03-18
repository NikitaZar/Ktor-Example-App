package com.nzar.ktor_app.di

import com.nzar.ktor_app.api.KtorApi
import com.nzar.ktor_app.api.KtorService
import com.nzar.ktor_app.ktorUtils.KtorUtils
import com.nzar.ktor_app.ktorUtils.KtorUtilsImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import org.koin.dsl.module

val ktorModule = module {

    single {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(
                    Json {
                        isLenient = true
                        prettyPrint = false
                        encodeDefaults = true
                        ignoreUnknownKeys = true
                        useArrayPolymorphism = false
                        allowStructuredMapKeys = true
                        allowSpecialFloatingPointValues = true
                    }
                )
            }
//            defaultRequest {
//                url("https://base.url")
//            }
            engine {
                config {
                    get<OkHttpClient.Builder.() -> OkHttpClient.Builder>().invoke(this)
                }
            }
        }
    }

    single<KtorApi> {
        KtorService(ktorUtils = get())
    }

    single {
        get<OkHttpClient.Builder.() -> OkHttpClient.Builder>()
            .invoke(OkHttpClient.Builder())
            .build()
    }

    factory<KtorUtils> {
        KtorUtilsImpl(client = get())
    }
}