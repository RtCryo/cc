package com.cc.infrastructure.validator

import com.cc.domain.model.Room
import org.springframework.stereotype.Service

@Service
class Validator (private val ldapValidator: LdapValidator,
                 private val personValidator: PersonValidator,
                 private val roomValidator: RoomValidator) {

    fun validateLdap(ldap: String) {
        ldapValidator.validate(ldap)
    }

    fun validateRoom(rooms: List<Room>) {
        roomValidator.validate(rooms)
    }

    fun validateRoomNumber(number: Int) {
        roomValidator.validateRoomNumber(number)
    }

    fun validatePersons(rooms: List<Room>) {
        personValidator.validate(rooms)
    }

}