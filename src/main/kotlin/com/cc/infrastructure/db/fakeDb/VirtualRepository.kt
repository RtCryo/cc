package com.cc.infrastructure.db.fakeDb

import com.cc.domain.model.Room
import com.cc.domain.service.DomainRepository
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

}