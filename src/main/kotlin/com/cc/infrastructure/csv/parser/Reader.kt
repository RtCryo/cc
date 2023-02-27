package com.cc.infrastructure.csv.parser

import org.springframework.stereotype.Service
import java.io.InputStream

@Service
class Reader {
    fun readCsv(inputStream: InputStream): List<RoomUserCase> {
        val reader = inputStream.bufferedReader().readLine()
        return reader.lineSequence()
                .filter { it.isNotBlank() }
                .map {
                    val roomWithPersons = it.split(',')
                    val toList = roomWithPersons.subList(1, roomWithPersons.size - 1).map {
                        val person = it.split(" ")
                        val ldap = person.last()
                        val secondName = person[person.lastIndex - 2]
                        val title = getTitleIfExist(person[0])
                        val middleName = getMiddleNameIfExist(person[2])
                        val firstName = if (title.isNullOrEmpty()) person[1] else person[0]
                        PersonUserCase(title = title,
                                firstName = firstName,
                                secondName = secondName,
                                middleName = middleName,
                                ldap = ldap)
                    }.toList()
                    RoomUserCase(roomNumber = roomWithPersons[0].toInt(), toList)
                }.toList()
    }

    private fun getMiddleNameIfExist(s: String): String? {
        val middleNames = listOf("van", "von", "de")
        if (middleNames.contains(s)) return s
        return null
    }

    private fun getTitleIfExist(s: String): String? {
        if (s == "Dr.") {
            return "Dr."
        }
        return null
    }
}