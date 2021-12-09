package eu.holker.classic.repositories.entities

import eu.holker.classic.services.dto.OpusDto
import javax.persistence.*

@Entity
@Table(name = "t_opus")
class OpusEntity(
    var name: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "composer_id")
    var composer: ComposerEntity = ComposerEntity("", "", "")
}

val OpusEntity.dto: OpusDto
    get() = OpusDto(name, id)