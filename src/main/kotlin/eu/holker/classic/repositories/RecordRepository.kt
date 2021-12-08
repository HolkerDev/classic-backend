package eu.holker.classic.repositories

import eu.holker.classic.repositories.entities.RecordEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RecordRepository : JpaRepository<RecordEntity, Int> {
}
