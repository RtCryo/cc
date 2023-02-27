package com.cc.domain.model

import com.cc.domain.exceptions.LdapInvalidException

data class Person(val title: Title?, val firstName:String, val secondName:String, val middleName: MiddleName?, val ldap: String) {

    init {
        require(ldap == firstName[0].lowercase() + secondName.lowercase()) {
            throw LdapInvalidException("$ldap for user: ${getTitleWhenExist()}$firstName ${getMiddleNameWhenExist()}$secondName invalid")
        }
    }

    private fun getTitleWhenExist():String {
        if (title !=null)
            return title.value + " "
        return ""
    }

    private fun getMiddleNameWhenExist():String {
        if (middleName !=null)
            return middleName.value + " "
        return ""
    }

}