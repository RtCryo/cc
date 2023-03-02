package com.cc.infrastructure.validator

import com.cc.infrastructure.exceptions.LdapInvalidException
import org.springframework.stereotype.Service

@Service
class LdapValidator {
    fun validate(str: String) {
        val ldap = str.split(" ").last()
        if ('(' != ldap[0] || ')' != ldap[ldap.lastIndex]) throw LdapInvalidException("Ldap not found: $str")
    }
}