package eu.holker.classic.services

import eu.holker.classic.repositories.OpusRepository
import eu.holker.classic.repositories.entities.dto
import eu.holker.classic.services.dto.OpusDto
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class OpusService(private val opusRepository: OpusRepository) {

    fun findOpusById(opusId: Int): Result<OpusDto> {
        val opus = opusRepository.findById(opusId)
        return if (opus.isPresent) {
            Result.success(opus.get().dto)
        } else {
            Result.failure(Exception("There is no opus with such id"))
        }
    }

    companion object : KLogging()
}