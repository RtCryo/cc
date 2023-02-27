package com.cc.domain.service

import com.cc.domain.model.Room
import java.io.InputStream

interface DomainReader {

    fun readCsv(inputStream: InputStream):List<Room>

}