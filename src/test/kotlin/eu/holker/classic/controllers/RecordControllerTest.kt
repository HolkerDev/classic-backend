package eu.holker.classic.controllers

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class RecordControllerTest {
    @Test
    fun test() {
        val controller = RecordController()
        val res = controller.getAll()
        res.statusCode shouldBeEqualTo HttpStatus.OK
    }
}