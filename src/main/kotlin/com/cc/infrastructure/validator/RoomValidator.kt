package com.cc.infrastructure.validator

import com.cc.domain.model.Room
import com.cc.infrastructure.exceptions.RequestRoomNumberInvalidException
import com.cc.infrastructure.exceptions.RoomIsNotUniqueException
import org.springframework.stereotype.Service

@Service
class RoomValidator {

    fun validate(rooms: List<Room>) {
        val toSet = rooms.map { it.roomNumber }.toSet()
        if (toSet.size != rooms.size) throw RoomIsNotUniqueException("Rooms is not unique")
    }

    fun validateRoomNumber(number: Int) {
        if (number < 1000 || number > 9999) throw RequestRoomNumberInvalidException("Room number is invalid: $number")
    }

}