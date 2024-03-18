package com.nzar.ktor_app.ktorUtils

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod

class KtorUtilsImpl(
    private val client: HttpClient,
) : KtorUtils {
    override suspend fun request(url: String, method: HttpMethod, block: HttpRequestBuilder.() -> Unit): HttpResponse? {
        return try {
            client.request(urlString = url) {
                this.apply {
                    this.method = method
                    addJsonHeader()
                    block()
                }
            }
        } catch (ste: SocketTimeoutException) {
            Log.i(REQUEST_TAG, ste.message.toString())
            null
        } catch (cte: ConnectTimeoutException) {
            Log.i(REQUEST_TAG, cte.message.toString())
            null
        } catch (e: Exception) {
            Log.i(REQUEST_TAG, e.message.toString())
            null
        }
    }

    companion object {
        private const val REQUEST_TAG = "Ktor request"
    }
}