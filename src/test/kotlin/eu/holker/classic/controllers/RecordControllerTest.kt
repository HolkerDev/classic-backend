package eu.holker.classic.controllers

import eu.holker.classic.services.RecordService
import eu.holker.classic.services.dto.ErrorDto
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
        res.body shouldBeEqualTo listOf<RecordDto>()
    }

    @Test
    fun `record controller getAll should return 200 when record list is not empty`() {
        val recordList = listOf(RecordDto(1, "dwa"), RecordDto(2, "dawd"))
        recordService.stub { on { findAllRecords() }.doReturn(recordList) }
        val res = controller.getAll()
        res.statusCode shouldBeEqualTo HttpStatus.OK
        res.body shouldBeEqualTo recordList
    }

    @Test
    fun `record controller getById should return 200 when record is found`() {
        val recordId = 12
        val dto = RecordDto(12, "dawdawd")
        recordService.stub { on { findRecordById(recordId) }.doReturn(Result.success(dto)) }
        val res = controller.getById(recordId = recordId.toString())
        res.statusCode shouldBeEqualTo HttpStatus.OK
        res.body shouldBeEqualTo dto
    }

    @Test
    fun `record controller getById should return 400 when record is not found`() {
        val recordId = 12
        recordService.stub { on { findRecordById(recordId) }.doReturn(Result.failure(Exception("Error message test"))) }
        val res = controller.getById(recordId = recordId.toString())
        res.statusCode shouldBeEqualTo HttpStatus.BAD_REQUEST
        res.body shouldBeEqualTo ErrorDto("Error message test")
    }
}
