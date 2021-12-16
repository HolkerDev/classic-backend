package eu.holker.classic.controllers

import eu.holker.classic.services.OpusService
import eu.holker.classic.services.dto.OpusDto
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import org.springframework.http.HttpStatus

internal class OpusControllerTest {

    private val opusService: OpusService = mock { }
    private val opusController = OpusController(opusService)

    @Test
    fun `get opus by id should return 200 is found`() {
        val opusId = 1
        val opusDto = OpusDto("name", 1)
        opusService.stub { on { findOpusById(opusId) }.doReturn(Result.success(opusDto)) }
        val response = opusController.findByOpusId(opusId)
        response.statusCode shouldBeEqualTo HttpStatus.OK
        response.body shouldBeEqualTo opusDto
    }

    @Test
    fun `get opus by id should return 404 is not found`() {
        val opusId = 1
        opusService.stub { on { findOpusById(opusId) }.doReturn(Result.failure(Exception("test message"))) }
        val response = opusController.findByOpusId(opusId)
        response.statusCode shouldBeEqualTo HttpStatus.NOT_FOUND
    }
}