package eu.holker.classic.repositories.entities

import javax.persistence.*

@Entity
@Table(name = "t_composer")
class ComposerEntity(
    var name: String,
    var firstName: String,
    var lastName: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0
}