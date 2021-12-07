package eu.holker.classic.controllers

import eu.holker.classic.repositories.entities.RecordEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/records")
class RecordController {

    @GetMapping("")
    fun getAll(): ResponseEntity<*> {
        return ResponseEntity.ok(listOf(RecordEntity("test")))
    }
}