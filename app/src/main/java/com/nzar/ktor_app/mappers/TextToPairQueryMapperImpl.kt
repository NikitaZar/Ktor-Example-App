package com.nzar.ktor_app.mappers

class TextToPairQueryMapperImpl : TextToPairQueryMapper {
    override fun map(queryText: String?): List<Pair<String, String>>? {
        return queryText.takeIf { !it.isNullOrEmpty() }?.split(NEW_QUERY_DELIMITER)?.map { row ->
            row.split(NAME_QUERY_DELIMITER).let {
                it[0] to it[1]
            }
        }
    }

    companion object {
        private const val NEW_QUERY_DELIMITER = "\n"
        private const val NAME_QUERY_DELIMITER = "-"
    }
}