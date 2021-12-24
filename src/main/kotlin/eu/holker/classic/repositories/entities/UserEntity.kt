package eu.holker.classic.repositories.entities

import eu.holker.classic.services.dto.UserDto
import javax.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    var email: String,
    var password: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
}

val UserEntity.dto: UserDto
    get() = UserDto(id, email)