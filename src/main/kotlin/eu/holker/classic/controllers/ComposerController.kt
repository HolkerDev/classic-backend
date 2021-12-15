package eu.holker.classic.controllers

import eu.holker.classic.services.ComposerService
import eu.holker.classic.services.dto.ComposerDto
import eu.holker.classic.services.dto.ErrorDto
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Composers")
@RestController
@RequestMapping("/composers")
class ComposerController(
    private val composerService: ComposerService
) {

    @GetMapping("")
    fun getComposersFiltered(@RequestParam(name = "lastname") lastName: String?): ResponseEntity<*> {
        var composers = listOf<ComposerDto>()
        lastName?.let {
            composers = composerService.findComposersByLastName(lastName)
        }
        return ResponseEntity.ok(composers)
    }

    @GetMapping("/{composerId}/opuses")
    fun getOpusesByComposer(@PathVariable composerId: Int): ResponseEntity<*> {
        return composerService.findOpusesByComposerId(composerId).fold(onSuccess = {
            ResponseEntity.ok(it)
        }, onFailure = {
            ResponseEntity.badRequest().body(ErrorDto(it.message))
        })
    }

    companion object : KLogging()
}
