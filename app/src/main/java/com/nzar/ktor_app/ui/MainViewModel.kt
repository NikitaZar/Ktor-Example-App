package com.nzar.ktor_app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nzar.ktor_app.data.NetRepository
import com.nzar.ktor_app.mappers.SpPositionToMethodMapper
import com.nzar.ktor_app.model.NetRepositoryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(
    private val netRepository: NetRepository,
    private val toMethodMapper: SpPositionToMethodMapper,
) : ViewModel() {

    val stateFlow: Flow<NetRepositoryModel> = netRepository.stateFlow

    fun request(url: String, methodPosition: Int, body: String, query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            netRepository.request(
                url = url,
                method = toMethodMapper.map(methodPosition),
                body = body.takeIf { it.isNotEmpty() },
                query = query.takeIf { it.isNotEmpty() }?.split("\n")
            )
        }
    }
}