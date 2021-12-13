package eu.holker.classic.repositories.entities

import eu.holker.classic.services.dto.RecordDto
import javax.persistence.*

@Entity
@Table(name = "t_record")
class RecordEntity(
    var link: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opus_id")
    var opus: OpusEntity = OpusEntity("")
}

val RecordEntity.dto: RecordDto
    get() = RecordDto(id, link, opus.name)
