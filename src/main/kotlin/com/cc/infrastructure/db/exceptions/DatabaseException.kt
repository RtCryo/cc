package com.cc.infrastructure.db.exceptions

open class DatabaseException(override val message:String): RuntimeException(message)