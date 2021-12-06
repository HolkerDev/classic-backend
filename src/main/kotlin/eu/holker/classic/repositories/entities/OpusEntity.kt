package eu.holker.classic.repositories.entities

import javax.persistence.*

@Entity
@Table(name = "t_opus")
class OpusEntity(
    var name: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0
}