package com.cc.application.service

import com.cc.application.model.PersonApp
import com.cc.application.model.RoomApp
import com.cc.domain.model.Person
import com.cc.domain.model.Room
import com.cc.domain.service.DomainService
import org.springframework.stereotype.Service
import java.io.InputStream

@Service
class ApplicationService(private val domainService: DomainService) {

    fun getRoom(roomNumber: Int): RoomApp {
        return domainService.getRoom(roomNumber).toApp()
    }

    fun transformAndSaveRoomsFromStream(inputStream: InputStream) {
        domainService.transformAndSaveRoomsFromStream(inputStream)
    }

    fun getAllRoom(): List<RoomApp> {
        return domainService.getAllRoom().map { it.toApp() }
    }
}

private fun Room.toApp(): RoomApp {
    return RoomApp(this.roomNumber, this.persons.map { it?.toApp() })
}

private fun Person.toApp(): PersonApp {
    return PersonApp(this.title?.value, this.firstName, this.secondName, this.middleName?.value, this.ldap)
}