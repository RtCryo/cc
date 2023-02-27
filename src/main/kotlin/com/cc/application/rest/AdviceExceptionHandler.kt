package com.cc.application.rest

import com.cc.application.rest.dto.MessageDto
import com.cc.domain.exceptions.DomainException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class AdviceExceptionHandler {

    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(DomainException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun domainExceptionHandler(domainException: DomainException):MessageDto {
        logger.error(domainException.message, domainException)
        return MessageDto(message = domainException.message, code = 400)
    }

}