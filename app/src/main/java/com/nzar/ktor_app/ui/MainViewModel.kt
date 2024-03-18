package com.nzar.ktor_app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nzar.ktor_app.mappers.SpPositionToMethodMapper
import com.nzar.ktor_app.mappers.TextToPairQueryMapper
import com.nzar.ktor_app.model.NetRepositoryModel
import com.nzar.ktor_app.repository.NetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(
    private val netRepository: NetRepository,
    private val toMethodMapper: SpPositionToMethodMapper,
    private val toPairQueryMapper: TextToPairQueryMapper
) : ViewModel() {

    val stateFlow: Flow<NetRepositoryModel> = netRepository.dataFlow

    fun request(url: String, methodPosition: Int, body: String, queryText: String) {
        viewModelScope.launch(Dispatchers.IO) {
            netRepository.request(
                url = url,
                method = toMethodMapper.map(methodPosition),
                body = body.takeIf { it.isNotEmpty() },
                query = toPairQueryMapper.map(queryText)
            )
        }
    }
}