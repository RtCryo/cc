package com.cc.infrastructure.csv.parser

import com.cc.domain.model.MiddleName
import com.cc.domain.model.Person
import com.cc.domain.model.Title
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.io.File
import java.io.FileInputStream

@SpringBootTest
class ReaderTest {

    @Autowired
    private lateinit var reader: Reader

    @Test
    fun readInputStreamWithRooms() {
        val xmlFile = File("src/test/resources/sitzplan.csv")
        val fileInputStream = FileInputStream(xmlFile)
        val expectedPerson = Person(
                firstName = "Frank",
                secondName = "Supper",
                ldap = "fsupper",
                title = Title("Dr."),
                middleName = MiddleName("von"))
        val expectedAnotherPerson = Person(
                firstName = "Dennis",
                secondName = "Fischer",
                ldap = "dfischer",
                title = null,
                middleName = null)

        val actual = reader.readCsv(fileInputStream)

        assertThat(actual).hasSize(15)
        assertThat(actual[0].roomNumber).isEqualTo(1111)
        assertThat(actual[0].persons[1]).isEqualTo(expectedPerson)
        assertThat(actual[0].persons[0]).isEqualTo(expectedAnotherPerson)
    }

}