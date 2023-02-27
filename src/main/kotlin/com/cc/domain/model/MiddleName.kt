package com.cc.domain.model

import com.cc.domain.exceptions.MiddleNameInvalidException

data class MiddleName(val value: String) {
    private val middleNamesPossible: List<String> = listOf("van", "von", "de")

    init {
        require(middleNamesPossible.contains(value)) {
            throw MiddleNameInvalidException("$value is invalid")
        }
    }

}