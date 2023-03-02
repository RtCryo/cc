package com.cc.domain.service

import com.cc.domain.model.Room
import org.springframework.stereotype.Service
import java.io.InputStream

@Service
class DomainService(private val repository: DomainRepository, private val reader: DomainReader) {
    fun transformAndSaveRoomsFromStream(inputStream: InputStream) {
        val rooms: List<Room> = reader.readCsv(inputStream)
        repository.saveAll(rooms)
    }

    fun getRoom(roomNumber: Int): Room {
        return repository.findByRoomNumber(roomNumber)
    }

    fun getAllRoom(): List<Room> {
        return repository.findAll()
    }
}