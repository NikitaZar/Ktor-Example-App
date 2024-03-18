package com.nzar.ktor_app.ktorUtils

import com.nzar.ktor_app.model.NetRepositoryModel
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse

fun HttpRequestBuilder.addJsonHeader() {
    headers.append("Content-Type", "application/json")
}

fun HttpResponse?.parseToNetRepositoryModel(): NetRepositoryModel {
    return NetRepositoryModel(inProgress = false, status = this?.status?.value, isRequestError = this == null)
}