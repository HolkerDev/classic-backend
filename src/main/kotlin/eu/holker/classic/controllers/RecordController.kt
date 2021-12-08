package eu.holker.classic.controllers

import eu.holker.classic.services.RecordService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/records")
class RecordController(
    private val recordService: RecordService,
) {

    @GetMapping("")
    fun getAll(): ResponseEntity<*> {
        val records = recordService.findAllRecords()
        return ResponseEntity.ok(records)
    }
}
