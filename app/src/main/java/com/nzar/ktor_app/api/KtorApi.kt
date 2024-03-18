package com.nzar.ktor_app.api

import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod

interface KtorApi {
    suspend fun request(url: String, method: HttpMethod, body: String?, query: List<Pair<String, String>>?): HttpResponse?
}