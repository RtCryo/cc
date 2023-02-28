package com.cc.infrastructure.csv.parser

import com.cc.domain.model.MiddleName
import com.cc.domain.model.Person
import com.cc.domain.model.Room
import com.cc.domain.model.Title
import com.cc.domain.service.DomainReader
import org.springframework.stereotype.Service
import java.io.InputStream

@Service
class Reader: DomainReader {

    override fun readCsv(inputStream: InputStream): List<Room> {
        val reader = inputStream.bufferedReader()
        return reader.lineSequence()
                .map {
                    val roomWithPersons = it.split(',')
                    val toList = roomWithPersons.subList(1, roomWithPersons.size)
                            .filter { s -> s.isNotBlank() }
                            .map { s -> s.trim() }
                            .map { person ->
                                val split = person.split(" ")
                                val ldap = getLdap(split)
                                val secondName = split[split.lastIndex - 1]
                                val title = getTitle(split)
                                val middleName = getMiddleName(split)
                                val firstName = getFirstName(split, title, middleName)
                                Person(title, firstName, secondName, middleName, ldap)
                            }.toList()
                    Room(roomNumber = roomWithPersons[0].toInt(), toList)
                }.toList()
    }

    private fun getFirstName(split: List<String>, title: Title?, middleName: MiddleName?): String {
        val firstIndex = if (title != null) 1 else 0
        val lastIndex = if (middleName != null) split.lastIndex - 2 else split.lastIndex - 1
        return split.subList(firstIndex, lastIndex).joinToString(separator = " ")
    }

    private fun getTitle(split: List<String>) = if (split.first() == "Dr.") Title(split.first()) else null

    private fun getMiddleName(split: List<String>) =
            if (listOf("von", "de", "van").contains(split[split.lastIndex - 2])) MiddleName(split[split.lastIndex - 2]) else null

    private fun getLdap(personArray: List<String>) = personArray.last().removeSurrounding("(", ")")
}
