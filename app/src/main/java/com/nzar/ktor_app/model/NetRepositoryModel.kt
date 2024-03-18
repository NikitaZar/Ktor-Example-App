package com.nzar.ktor_app.model

data class NetRepositoryModel(
    val inProgress: Boolean,
    val status: Int?,
    val isRequestError: Boolean,
) {
    companion object {
        val inProgressState = NetRepositoryModel(
            inProgress = true,
            status = null,
            isRequestError = false,
        )
    }
}
