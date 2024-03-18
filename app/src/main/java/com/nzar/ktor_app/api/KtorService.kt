package com.nzar.ktor_app.api

import com.nzar.ktor_app.ktorUtils.KtorUtils
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod

class KtorService(
    private val ktorUtils: KtorUtils
) : KtorApi {
    override suspend fun request(url: String, method: HttpMethod, body: String?, query: List<Pair<String, String>>?): HttpResponse? {
        return ktorUtils.request(
            url = url,
            method = method,
        ) {
            setBody(body)
            url {
                query?.forEach { (name, v) ->
                    parameters.append(name, v)
                }
            }
        }
    }
}