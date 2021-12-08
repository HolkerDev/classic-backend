package eu.holker.classic.services

import eu.holker.classic.repositories.ComposerRepository
import eu.holker.classic.repositories.entities.dto
import eu.holker.classic.services.dto.ComposerDto
import org.springframework.stereotype.Service

@Service
class ComposerService(
    private val composerRepository: ComposerRepository,
) {
    fun findAllComposers(): List<ComposerDto> {
        return composerRepository.findAll().map { it.dto }
    }
}
