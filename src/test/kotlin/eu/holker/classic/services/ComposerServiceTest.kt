package eu.holker.classic.services

import eu.holker.classic.repositories.ComposerRepository
import eu.holker.classic.repositories.entities.ComposerEntity
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


}