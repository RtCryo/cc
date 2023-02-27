package com.cc.domain.model

import com.cc.domain.exceptions.TitleInvalidException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class TitleTest {

    @Test
    fun createTitleWhenInvalid() {
        assertThatThrownBy { Title("Rt.") }
                .isInstanceOf(TitleInvalidException::class.java)
                .hasMessage("Title Rt. is invalid")
    }

    @Test
    fun createTitle() {
        val expected = Title("Dr.")
        assertThat(Title("Dr."))
                .isEqualTo(expected)
    }
}