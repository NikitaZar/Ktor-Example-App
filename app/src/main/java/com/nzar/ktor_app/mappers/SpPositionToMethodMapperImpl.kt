package com.nzar.ktor_app.mappers

import io.ktor.http.HttpMethod

class SpPositionToMethodMapperImpl : SpPositionToMethodMapper {
    override fun map(position: Int): HttpMethod {
        return when (position) {
            0 -> HttpMethod.Get
            1 -> HttpMethod.Post
            2 -> HttpMethod.Put
            3 -> HttpMethod.Delete
            else -> throw IllegalStateException("Unknown position: $position")
        }
    }
}