package eu.holker.classic.services

import eu.holker.classic.repositories.RecordRepository
import eu.holker.classic.repositories.entities.dto
import eu.holker.classic.services.dto.RecordDto
import org.springframework.stereotype.Service

@Service
class RecordService(
    private val recordRepository: RecordRepository,
) {
    fun findAllRecords(): List<RecordDto> = recordRepository.findAll().map { it.dto }
}
