package eu.holker.classic.repositories

import eu.holker.classic.repositories.entities.OpusEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OpusRepository : JpaRepository<OpusEntity, Int>