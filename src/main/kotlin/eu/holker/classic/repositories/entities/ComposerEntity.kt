package eu.holker.classic.repositories.entities

import eu.holker.classic.services.dto.ComposerDto
import javax.persistence.*

@Entity
@Table(name = "t_composer")
class ComposerEntity(
    var name: String,
    var firstName: String?,
    var lastName: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @OneToMany(mappedBy = "composer", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var opuses: List<OpusEntity> = arrayListOf()
}

val ComposerEntity.dto: ComposerDto
    get() = ComposerDto(id, name, firstName ?: "", lastName)
