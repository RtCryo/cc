package com.cc.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PersonTest {

    @Test
    fun createPerson() {
        val expected = Person(firstName = "fname", secondName = "sname", ldap = "fsname", middleName = MiddleName("von"), title = Title("Dr."))
        assertThat(Person(firstName = "fname", secondName = "sname", ldap = "fsname", middleName = MiddleName("von"), title = Title("Dr.")))
                .isEqualTo(expected)
    }
}