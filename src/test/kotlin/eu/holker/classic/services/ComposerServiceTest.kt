package eu.holker.classic.services

import eu.holker.classic.TestFactory.composerEntity
import eu.holker.classic.repositories.ComposerRepository
import eu.holker.classic.repositories.entities.ComposerEntity
import eu.holker.classic.repositories.entities.OpusEntity
import eu.holker.classic.repositories.entities.dto
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import java.util.*

internal class ComposerServiceTest {
    private val composerRepository: ComposerRepository = mock { }
    private val composerService = ComposerService(composerRepository)

    @Test
    fun `should return composer dto on findById if composer exists`() {
        val composerId = 1
        val composerEntity = ComposerEntity("name", "first_name", "lastname")
        composerRepository.stub { on { findById(composerId) }.doReturn(Optional.of(composerEntity)) }
        val composerResult = composerService.findComposerById(composerId)
        composerResult.isSuccess shouldBe true
        val composer = composerResult.getOrNull()
        composer shouldBeEqualTo composerEntity.dto
    }

    @Test
    fun `should return exception on findById if composer doesn't exist`() {
        val composerId = 1
        composerRepository.stub {
            on { findById(composerId) }.doReturn(Optional.empty())
        }
        val composerResult = composerService.findComposerById(composerId)
        composerResult.isFailure shouldBeEqualTo true
        composerResult.exceptionOrNull() shouldBeInstanceOf Exception::class
    }

    @Test
    fun `should return empty list on findComposerByLastname if there are not composers`() {
        val composerLastName = "Smitt"
        composerRepository.stub { on { findByLastNameContains(composerLastName) }.doReturn(listOf()) }
        val composers = composerService.findComposersByLastName(composerLastName)
        composers.size shouldBe 0
    }

    @Test
    fun `should return 2 composers on findComposerByLastname if there are 2 composers`() {
        val composerLastName = "Smitt"
        val composerEntities =
            listOf(ComposerEntity("name1", null, "lastname1"), ComposerEntity("name2", "firstName1", "lastname2"))
        composerRepository.stub { on { findByLastNameContains(composerLastName) }.doReturn(composerEntities) }
        val composers = composerService.findComposersByLastName(composerLastName)
        composers.size shouldBe 2
        composers shouldBeEqualTo composerEntities.map { it.dto }
    }

    @Test
    fun `should return opuses on findOpusesByComposerId if there are opuses`() {
        val composerId = 1
        val composerEntity = composerEntity()
        composerEntity.opuses = listOf(OpusEntity("1"))
        composerRepository.stub { on { findById(composerId) }.doReturn(Optional.of(composerEntity)) }
        val opusesResult = composerService.findOpusesByComposerId(composerId)
        opusesResult.isSuccess shouldBe true
        val opuses = opusesResult.getOrNull()
        opuses?.size shouldBeEqualTo 1
    }

    @Test
    fun `should return exception on findOpusesByComposerId if there is no composer with id`() {
        val composerId = 1
        composerRepository.stub { on { findById(composerId) }.doReturn(Optional.empty()) }
        val opusesResult = composerService.findOpusesByComposerId(composerId)
        opusesResult.isSuccess shouldBe false
        opusesResult.exceptionOrNull() shouldBeInstanceOf Exception::class
    }
}