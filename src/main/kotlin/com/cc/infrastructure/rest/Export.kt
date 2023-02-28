package com.cc.infrastructure.rest

import com.cc.application.service.ApplicationService
import com.cc.infrastructure.rest.dto.RoomDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class Export(private val applicationService: ApplicationService) {

    @GetMapping("/api/room/{roomNumber}")
    fun getRoom(@PathVariable roomNumber: Int): RoomDto {
        return applicationService.getRoom(roomNumber)
    }

    @GetMapping("/api/room")
    fun getAllRoom(): List<RoomDto> {
        return applicationService.getAllRoom()
    }

}