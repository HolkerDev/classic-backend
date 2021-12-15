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
    fun test() {
        val composerId = 1
        val composerEntity = ComposerEntity("name", "first_name", "lastname")
        composerRepository.stub { on { findById(composerId) }.doReturn(Optional.of(composerEntity)) }
        val composerResult = composerService.findComposerById(composerId)
        composerResult.isSuccess shouldBe true
        val composer = composerResult.getOrNull()
        composer shouldBeEqualTo composerEntity.dto
    }

    @Test
    fun test1() {
        val composerId = 1
        composerRepository.stub {
            on { findById(composerId) }.doReturn(Optional.empty())
        }
        val composerResult = composerService.findComposerById(composerId)
        composerResult.isFailure shouldBeEqualTo true
        composerResult.exceptionOrNull() shouldBeInstanceOf Exception::class
    }

    @Test
    fun test2() {
        val composerLastName = "Smitt"
        composerRepository.stub { on { findByLastNameContains(composerLastName) }.doReturn(listOf()) }
        val composers = composerService.findComposersByLastName(composerLastName)
        composers.size shouldBe 0
    }

    @Test
    fun test3() {
        val composerLastName = "Smitt"
        val composerEntities =
            listOf(ComposerEntity("name1", null, "lastname1"), ComposerEntity("name2", "firstName1", "lastname2"))
        composerRepository.stub { on { findByLastNameContains(composerLastName) }.doReturn(composerEntities) }
        val composers = composerService.findComposersByLastName(composerLastName)
        composers.size shouldBe 2
        composers shouldBeEqualTo composerEntities.map { it.dto }
    }

    @Test
    fun test4() {
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
    fun test5() {
        val composerId = 1
        composerRepository.stub { on { findById(composerId) }.doReturn(Optional.empty()) }
        val opusesResult = composerService.findOpusesByComposerId(composerId)
        opusesResult.isSuccess shouldBe false
        opusesResult.exceptionOrNull() shouldBeInstanceOf Exception::class
    }
}