package com.nzar.ktor_app.ktorUtils

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod

interface KtorUtils {
    suspend fun request(url: String, method: HttpMethod, block: HttpRequestBuilder.() -> Unit = {}): HttpResponse?
}