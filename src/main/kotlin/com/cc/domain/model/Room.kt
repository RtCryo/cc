package com.cc.domain.model

import com.cc.domain.exceptions.RoomNumberInvalidExceptions

data class Room(val roomNumber: Int, val persons: List<Person?>) {

    private val roomNumberPattern = "\\d{4}".toRegex()

    init {
        require(roomNumber.toString().matches(roomNumberPattern)) {
            throw RoomNumberInvalidExceptions("Room number: $roomNumber invalid")
        }
    }

}

