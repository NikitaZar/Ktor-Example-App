package com.nzar.ktor_app.repository

import com.nzar.ktor_app.api.KtorApi
import com.nzar.ktor_app.ktorUtils.parseToNetRepositoryModel
import com.nzar.ktor_app.model.NetRepositoryModel
import io.ktor.http.HttpMethod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class NetRepositoryImpl(
    private val api: KtorApi
) : NetRepository {

    override val dataFlow: Flow<NetRepositoryModel>
        get() = _dataFlow
    private val _dataFlow: MutableSharedFlow<NetRepositoryModel> = MutableSharedFlow()

    override suspend fun request(url: String, method: HttpMethod, body: String?, query: List<Pair<String, String>>?) {
        _dataFlow.emit(NetRepositoryModel.inProgressState)
        api.request(
            url = url,
            method = method,
            body = body,
            query = query
        ).parseToNetRepositoryModel().let { _dataFlow.emit(it) }
    }
}