package com.nzar.ktor_app.mappers

import io.ktor.http.HttpMethod

interface SpPositionToMethodMapper {
    fun map(position: Int): HttpMethod
}