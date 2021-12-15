package eu.holker.classic

import eu.holker.classic.repositories.entities.ComposerEntity

object TestFactory {
    fun composerEntity(): ComposerEntity = ComposerEntity("name", "firstName", "lastname")
}