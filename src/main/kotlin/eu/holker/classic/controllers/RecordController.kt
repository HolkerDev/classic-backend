package eu.holker.classic.controllers

import eu.holker.classic.services.RecordService
import eu.holker.classic.services.dto.ErrorDto
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Records")
@RestController
@RequestMapping("/records")
class RecordController(
    private val recordService: RecordService,
) {
    @GetMapping("/{recordId}")
    fun getById(@PathVariable recordId: String): ResponseEntity<*> {
        recordService.findRecordById(recordId.toInt())
            .fold(onSuccess = { record -> return ResponseEntity.ok(record) },
                onFailure = { e -> return ResponseEntity.badRequest().body(ErrorDto(e.message)) })
    }
}
