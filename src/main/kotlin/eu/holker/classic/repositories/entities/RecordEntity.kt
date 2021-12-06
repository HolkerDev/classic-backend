package eu.holker.classic.repositories.entities

import javax.persistence.*

@Entity
@Table(name = "t_record")
class RecordEntity(
    var link: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0
}