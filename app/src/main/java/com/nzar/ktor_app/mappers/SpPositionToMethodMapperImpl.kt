package com.nzar.ktor_app.mappers

import io.ktor.http.HttpMethod

class SpPositionToMethodMapperImpl : SpPositionToMethodMapper {
    override fun map(position: Int): HttpMethod {
        return when (position) {
            1 -> HttpMethod.Get
            2 -> HttpMethod.Post
            3 -> HttpMethod.Put
            4 -> HttpMethod.Delete
            else -> throw IllegalStateException("Unknown position")
        }
    }
}