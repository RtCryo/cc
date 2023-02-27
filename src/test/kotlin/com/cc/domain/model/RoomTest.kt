package com.cc.domain.model

import com.cc.domain.exceptions.RoomNumberInvalidExceptions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class RoomTest {

    @Test
    fun createRoomWhenRoomNumberInvalid() {
        assertThatThrownBy { Room(123, listOf()) }
                .isInstanceOf(RoomNumberInvalidExceptions::class.java)
                .hasMessage("Room number: 123 invalid")
    }

    @Test
    fun createRoom() {
        val expected = Room(1234, listOf())
        assertThat(Room(1234, listOf()))
                .isEqualTo(expected)
    }
}

