package com.cc.infrastructure.db.fakeDb

import com.cc.domain.model.Room
import com.cc.domain.service.DomainRepository
import com.cc.infrastructure.db.exceptions.RoomNotFoundException
import org.springframework.stereotype.Repository

@Repository
class VirtualRepository: DomainRepository {

    private var rooms = ArrayList<Room>()

    override fun deleteAll() {
        rooms.clear()
    }

    override fun addRoom(room: Room) {
        rooms.add(room)
    }

    override fun saveAll(rooms: Iterable<Room>) {
        this.deleteAll()
        this.rooms.addAll(rooms)
    }

    override fun findByRoomNumber(roomNumber: Int): Room {
        return rooms.find { room: Room -> room.roomNumber == roomNumber }
                ?: throw RoomNotFoundException("Room is not available")
    }

    override fun findAll(): List<Room> {
        return rooms
    }

}