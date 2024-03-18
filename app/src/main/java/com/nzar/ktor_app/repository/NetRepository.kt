package com.nzar.ktor_app.repository

import com.nzar.ktor_app.model.NetRepositoryModel
import io.ktor.http.HttpMethod
import kotlinx.coroutines.flow.Flow

interface NetRepository {
    val dataFlow: Flow<NetRepositoryModel>
    suspend fun request(url: String, method: HttpMethod, body: String?, query: List<Pair<String, String>>?)
}