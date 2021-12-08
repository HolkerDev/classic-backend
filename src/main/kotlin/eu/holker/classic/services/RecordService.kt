package eu.holker.classic.services

import eu.holker.classic.repositories.RecordRepository
import eu.holker.classic.repositories.entities.dto
import eu.holker.classic.services.dto.RecordDto
import org.springframework.stereotype.Service
import kotlin.Exception

@Service
class RecordService(
    private val recordRepository: RecordRepository,
) {
    fun findAllRecords(): List<RecordDto> = recordRepository.findAll().map { it.dto }

    fun findRecordById(recordId: Int): Result<RecordDto> {
        val recordEntity = recordRepository.findById(recordId)
        return if (recordEntity.isEmpty) {
            Result.failure(Exception("There is no record with provided id"))
        } else {
            Result.success(recordEntity.get().dto)
        }
    }
}
