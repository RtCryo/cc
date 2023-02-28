package com.cc.infrastructure.rest

import com.cc.application.model.PersonApp
import com.cc.application.model.RoomApp
import com.cc.application.service.ApplicationService
import com.cc.infrastructure.rest.dto.PersonDto
import com.cc.infrastructure.rest.dto.RoomDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class Export(private val applicationService: ApplicationService) {

    @GetMapping("/api/room/{roomNumber}")
    fun getRoom(@PathVariable roomNumber: Int): RoomDto {
        return applicationService.getRoom(roomNumber).toDto()
    }

    @GetMapping("/api/room")
    fun getAllRoom(): List<RoomDto> {
        return applicationService.getAllRoom().map { it.toDto() }
    }

}

private fun RoomApp.toDto(): RoomDto {
    return RoomDto(this.roomNumber, persons = persons.map { it?.toDto() })
}

private fun PersonApp.toDto(): PersonDto {
    return PersonDto(this.title, this.firstName, this.secondName, this.middleName, this.ldap)
}