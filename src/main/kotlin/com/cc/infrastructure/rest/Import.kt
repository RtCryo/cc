package com.cc.infrastructure.rest

import com.cc.application.service.ApplicationService
import com.cc.infrastructure.rest.dto.MessageDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class Import(private val applicationService: ApplicationService) {

    @PostMapping("/api/import")
    @ResponseStatus(HttpStatus.OK)
    fun importCsv(@RequestBody file: MultipartFile): MessageDto {
        applicationService.transformAndSaveRoomsFromStream(file.inputStream)
        return MessageDto(message = "OK", code = 200)
    }

}