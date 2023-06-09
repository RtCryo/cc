package com.cc.domain.service

import com.cc.domain.model.Room

interface DomainRepository {

    fun deleteAll()

    fun saveAll(rooms: Iterable<Room>)

    fun findByRoomNumber(roomNumber: Int): Room

    fun findAll(): List<Room>

}