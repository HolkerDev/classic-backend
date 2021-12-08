package eu.holker.classic.controllers

import eu.holker.classic.services.RecordService
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.http.HttpStatus

class RecordControllerTest {
    private val recordService = mock<RecordService> { }
    private val controller = RecordController(recordService)

    @Test
    fun test() {
        whenever(recordService.findAllRecords()).doReturn(listOf())
        val res = controller.getAll()
        res.statusCode shouldBeEqualTo HttpStatus.OK
    }
}
