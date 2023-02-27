package com.cc.domain.model

import com.cc.domain.exceptions.TitleInvalidException

data class Title(val value: String) {
    private val titles: List<String> = listOf("Dr.")

    init {
        require(titles.contains(value)) {
            throw TitleInvalidException("Title $value is invalid")
        }
    }

}