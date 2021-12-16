package eu.holker.classic.controllers

import eu.holker.classic.services.OpusService
import eu.holker.classic.services.dto.ErrorDto
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "opuses")
@RestController
@RequestMapping("/opuses")
class OpusController(private val opusService: OpusService) {

    @GetMapping("/{opusId}")
    fun findByOpusId(@PathVariable opusId: Int): ResponseEntity<*> {
        return opusService.findOpusById(opusId).fold(onSuccess = {
            ResponseEntity.ok(it)
        }, onFailure = { ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDto(it.message)) })
    }

    companion object : KLogging()
}