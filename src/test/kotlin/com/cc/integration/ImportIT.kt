package com.cc.integration

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.io.File

@SpringBootTest
@AutoConfigureMockMvc
class ImportIT(@Autowired private val mockMvc: MockMvc) {

    @Test
    fun import() {
        mockMvc.perform(multipart("/api/import").file(createMockFile("sitzplan.csv")))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.code").value("1"))
    }

    @Test
    fun importWhenRoomNumberInvalid() {
        mockMvc.perform(multipart("/api/import").file(createMockFile("sitzplan_with_invalid_room_number.csv")))
                .andExpect(status().isBadRequest)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Room number: 11115 invalid"))
                .andExpect(jsonPath("$.code").value("4"))
    }

    @Test
    fun importWhenLdapInvalid() {
        mockMvc.perform(multipart("/api/import").file(createMockFile("sitzplan_with_invalid_ldap.csv")))
                .andExpect(status().isBadRequest)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Ldap not found: Dennis Fischer pfischer)"))
                .andExpect(jsonPath("$.code").value("4"))
    }

    @Test
    fun importWhenRoomNotUnique() {
        mockMvc.perform(multipart("/api/import").file(createMockFile("sitzplan_with_not_unique_rooms.csv")))
                .andExpect(status().isBadRequest)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Rooms is not unique"))
                .andExpect(jsonPath("$.code").value("2"))
    }

    @Test
    fun importWhenPersonNotUnique() {
        mockMvc.perform(multipart("/api/import").file(createMockFile("sitzplan_with_not_unique_persons.csv")))
                .andExpect(status().isBadRequest)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Persons is not unique"))
                .andExpect(jsonPath("$.code").value("3"))
    }

    private fun createMockFile(filename: String): MockMultipartFile {
        val file = File("src/test/resources/$filename")
        val inputStream = file.inputStream()
        return MockMultipartFile("file", "sitzplan.csv", MediaType.APPLICATION_JSON_VALUE, inputStream)
    }
}