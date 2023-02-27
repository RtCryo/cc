package com.cc.application.rest

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class Import {

    @PostMapping("/api/import")
    @ResponseStatus(HttpStatus.OK)
    fun importCsv(@RequestBody file: MultipartFile) {

    }

}