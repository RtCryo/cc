package com.cc.domain.model

import com.cc.domain.exceptions.LdapInvalidException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class PersonTest {

    @Test
    fun createPersonWhenLdapInvalid() {
        assertThatThrownBy {
            Person(firstName = "fname", secondName = "sname", ldap = "dsname", middleName = null, title = null) }
                .isInstanceOf(LdapInvalidException::class.java)
                .hasMessage("dsname for user: fname sname invalid")

        assertThatThrownBy {
            Person(firstName = "fname", secondName = "sname", ldap = "dsname", middleName = MiddleName("von"), title = Title("Dr.")) }
                .isInstanceOf(LdapInvalidException::class.java)
                .hasMessage("dsname for user: Dr. fname von sname invalid")
    }

    @Test
    fun createPerson() {
        val expected = Person(firstName = "fname", secondName = "sname", ldap = "fsname", middleName = MiddleName("von"), title = Title("Dr."))
        assertThat(Person(firstName = "fname", secondName = "sname", ldap = "fsname", middleName = MiddleName("von"), title = Title("Dr.")))
                .isEqualTo(expected)
    }
}