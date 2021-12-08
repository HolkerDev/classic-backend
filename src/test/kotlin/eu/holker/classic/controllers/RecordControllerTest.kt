package eu.holker.classic.controllers

import eu.holker.classic.services.RecordService
import eu.holker.classic.services.dto.RecordDto
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import org.springframework.http.HttpStatus

class RecordControllerTest {
    private val recordService = mock<RecordService> { }
    private val controller = RecordController(recordService)

    @Test
    fun `record controller getAll should return 200 when record list is empty`() {
        recordService.stub { on { findAllRecords() }.doReturn(listOf()) }
        val res = controller.getAll()
        res.statusCode shouldBeEqualTo HttpStatus.OK
    }

    @Test
    fun `record controller getById should return 200 when record is found`() {
        val recordId = 12
        recordService.stub { on { findRecordById(recordId) }.doReturn(Result.success(RecordDto(12, "dawdaw"))) }
        val res = controller.getById(recordId = recordId.toString())
        res.statusCode shouldBeEqualTo HttpStatus.OK
    }

    @Test
    fun `record controller getById should return 400 when record is not found`() {
        val recordId = 12
        recordService.stub { on { findRecordById(recordId) }.doReturn(Result.failure(Exception("Error message test"))) }
        val res = controller.getById(recordId = recordId.toString())
        res.statusCode shouldBeEqualTo HttpStatus.BAD_REQUEST
    }
}
