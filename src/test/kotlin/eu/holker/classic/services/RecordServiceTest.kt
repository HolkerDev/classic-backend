package eu.holker.classic.services

import eu.holker.classic.repositories.RecordRepository
import eu.holker.classic.repositories.entities.RecordEntity
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import java.util.*

internal class RecordServiceTest {

    private val recordRepository: RecordRepository = mock {}
    private val recordService = RecordService(recordRepository)

    @Test
    fun test1() {
        val recordId = 1
        recordRepository.stub {
            on { findById(recordId) }.doReturn(Optional.of(RecordEntity("1")))
        }
        val result = recordService.findRecordById(recordId)
        result.isSuccess shouldBeEqualTo true
    }

    @Test
    fun test2() {
        val recordId = 1
        recordRepository.stub {
            on { findById(recordId) }.doReturn(Optional.empty())
        }
        val result = recordService.findRecordById(recordId)
        result.isSuccess shouldBeEqualTo false
    }
}