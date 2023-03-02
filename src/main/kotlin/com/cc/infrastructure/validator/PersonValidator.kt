package com.cc.infrastructure.validator

import com.cc.domain.model.Room
import com.cc.infrastructure.exceptions.PersonIsNotUniqueException
import org.springframework.stereotype.Service

@Service
class PersonValidator {

    fun validate(rooms: List<Room>) {
        val toList = rooms.map { it.persons }.flatten().toList()
        if(toList.toSet().size != toList.size) throw PersonIsNotUniqueException("Persons is not unique")
    }

}