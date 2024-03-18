package com.nzar.ktor_app.mappers

interface TextToPairQueryMapper {
    fun map(queryText: String?): List<Pair<String, String>>?
}