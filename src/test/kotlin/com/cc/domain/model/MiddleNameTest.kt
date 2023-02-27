package com.cc.domain.model

import com.cc.domain.exceptions.MiddleNameInvalidException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class MiddleNameTest {

    @Test
    fun createMiddleNameWhenInvalid() {
        assertThatThrownBy { MiddleName("name") }
                .isInstanceOf(MiddleNameInvalidException::class.java)
                .hasMessage("name is invalid")
    }

    @Test
    fun createMiddleName() {
        val expected = MiddleName("van")
        assertThat(MiddleName("van"))
                .isEqualTo(expected)
    }
}