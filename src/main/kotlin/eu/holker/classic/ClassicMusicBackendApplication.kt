package eu.holker.classic

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ClassicMusicBackendApplication

fun main(args: Array<String>) {
    runApplication<ClassicMusicBackendApplication>(*args)
}
