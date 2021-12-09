package eu.holker.classic.services

import eu.holker.classic.repositories.ComposerRepository
import eu.holker.classic.repositories.entities.dto
import eu.holker.classic.services.dto.ComposerDto
import eu.holker.classic.services.dto.OpusDto
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class ComposerService(
    private val composerRepository: ComposerRepository,
) {
    fun findAllComposers(): List<ComposerDto> {
        return composerRepository.findAll().map {
            it.dto
        }
    }

    fun findOpusesByComposerId(composerId: Int): Result<List<OpusDto>> {
        val composer = composerRepository.findById(composerId)
        return if (composer.isPresent) {
            Result.success(composer.get().opuses.map { it.dto })
        } else {
            Result.failure(Exception("There is no composer with such id"))
        }
    }

    companion object : KLogging()
}
