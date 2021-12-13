package eu.holker.classic.repositories

import eu.holker.classic.repositories.entities.ComposerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ComposerRepository : JpaRepository<ComposerEntity, Int> {
    fun findByLastName(lastName: String): List<ComposerEntity>
}
