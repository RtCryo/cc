package com.cc.integration

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.io.File

@SpringBootTest
@AutoConfigureMockMvc
class ExportIT(@Autowired private val mockMvc: MockMvc) {

    @BeforeEach
    fun setUp() {
        mockMvc.perform(multipart("/api/import").file(createMockFile()))
                .andExpect(status().isOk)
    }

    @Test
    fun exportRoom() {
        mockMvc.perform(get("/api/room/1111"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.roomNumber").value("1111"))
                .andExpect(jsonPath("$.persons[0].title").value(null))
                .andExpect(jsonPath("$.persons[0].firstName").value("Dennis"))
                .andExpect(jsonPath("$.persons[0].secondName").value("Fischer"))
                .andExpect(jsonPath("$.persons[0].middleName").value(null))
                .andExpect(jsonPath("$.persons[0].ldap").value("dfischer"))

        mockMvc.perform(get("/api/room/1102"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.roomNumber").value("1102"))
                .andExpect(jsonPath("$.persons").isEmpty)
    }

    @Test
    fun exportAllRooms() {
        mockMvc.perform(get("/api/room"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].roomNumber").value("1111"))
                .andExpect(jsonPath("$[0].persons[0].title").value(null))
                .andExpect(jsonPath("$[0].persons[0].firstName").value("Dennis"))
                .andExpect(jsonPath("$[0].persons[0].secondName").value("Fischer"))
                .andExpect(jsonPath("$[0].persons[0].middleName").value(null))
                .andExpect(jsonPath("$[0].persons[0].ldap").value("dfischer"))
    }

    @Test
    fun exportWhenRoomNotValid() {
        mockMvc.perform(get("/api/room/555"))
                .andExpect(status().isBadRequest)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Room number is invalid: 555"))
                .andExpect(jsonPath("$.code").value(6))

        mockMvc.perform(get("/api/room/55555"))
                .andExpect(status().isBadRequest)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Room number is invalid: 55555"))
                .andExpect(jsonPath("$.code").value(6))
    }

    @Test
    fun exportWhenRoomNotFound() {
        mockMvc.perform(get("/api/room/6666"))
                .andExpect(status().isNotFound)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Room is not available"))
                .andExpect(jsonPath("$.code").value(5))
    }

    private fun createMockFile(): MockMultipartFile {
        val file = File("src/test/resources/sitzplan.csv")
        val inputStream = file.inputStream()
        return MockMultipartFile("file", "sitzplan.csv", MediaType.APPLICATION_JSON_VALUE, inputStream)
    }
}