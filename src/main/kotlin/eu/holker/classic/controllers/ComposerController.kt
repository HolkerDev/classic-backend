package eu.holker.classic.controllers

import eu.holker.classic.services.ComposerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/composers")
class ComposerController(
    private val composerService: ComposerService
) {

    @GetMapping("")
    fun getAll(): ResponseEntity<*> {
        val composers = composerService.findAllComposers()
        return ResponseEntity.ok(composers)
    }
}
