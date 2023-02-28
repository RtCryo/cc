package com.cc.infrastructure.rest

import com.cc.domain.exceptions.DomainException
import com.cc.infrastructure.db.exceptions.DatabaseException
import com.cc.infrastructure.rest.dto.MessageDto
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class AdviceExceptionHandler {

    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(DomainException::class)
    fun domainExceptionHandler(domainException: DomainException): ResponseEntity<MessageDto> {
        logger.error(domainException.message, domainException)
        return ResponseEntity(
                MessageDto(message = domainException.message, code = 4),
                HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(DatabaseException::class)
    fun databaseExceptionHandler(databaseException: DatabaseException): ResponseEntity<MessageDto> {
        logger.error(databaseException.message, databaseException)
        return ResponseEntity(
                MessageDto(message = databaseException.message, code = 5),
                HttpStatus.NOT_FOUND)
    }

}