package com.nzar.ktor_app.ktorUtils

import io.ktor.client.request.HttpRequestBuilder

fun HttpRequestBuilder.addJsonHeader() {
    headers.append("Content-Type", "application/json")
}