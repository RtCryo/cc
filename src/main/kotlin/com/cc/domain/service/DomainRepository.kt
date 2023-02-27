package com.cc.domain.service

import com.cc.domain.model.Room

interface DomainRepository {

    fun deleteAll()

    fun addRoom(room: Room)

}