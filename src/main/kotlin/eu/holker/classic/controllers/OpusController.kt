package eu.holker.classic.controllers

import io.swagger.v3.oas.annotations.tags.Tag
import mu.KLogging
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "opuses")
@RestController
@RequestMapping("/opuses")
class OpusController {
    companion object : KLogging()
}