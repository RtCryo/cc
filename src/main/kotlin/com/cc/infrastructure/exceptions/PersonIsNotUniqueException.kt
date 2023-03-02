package com.cc.infrastructure.exceptions

class PersonIsNotUniqueException(override val message: String) : RuntimeException(message)
