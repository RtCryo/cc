package com.cc.application.service

import com.cc.domain.model.Person
import com.cc.domain.model.Room
import com.cc.domain.service.DomainService
import com.cc.infrastructure.rest.dto.PersonDto
import com.cc.infrastructure.rest.dto.RoomDto
import org.springframework.stereotype.Service
import java.io.InputStream

@Service
class ApplicationService(private val domainService: DomainService) {

    fun getRoom(roomNumber: Int): RoomDto {
        return domainService.getRoom(roomNumber).toDto()
    }

    fun transformAndSaveRoomsFromStream(inputStream: InputStream) {
        domainService.transformAndSaveRoomsFromStream(inputStream)
    }

    fun getAllRoom(): List<RoomDto> {
        return domainService.getAllRoom().map { it.toDto() }
    }
}

private fun Room.toDto(): RoomDto {
    return RoomDto(this.roomNumber, this.persons.map {it?.toDto()})
}

private fun Person.toDto(): PersonDto {
    return PersonDto(this.title?.value, this.firstName, this.secondName, this.middleName?.value, this.ldap)
}