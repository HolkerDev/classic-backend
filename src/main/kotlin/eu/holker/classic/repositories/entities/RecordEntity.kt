package eu.holker.classic.repositories.entities

import eu.holker.classic.services.dto.RecordDto
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
@Table(name = "t_record")
class RecordEntity(
    var link: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0
}

val RecordEntity.dto: RecordDto
    get() = RecordDto(id, link)
