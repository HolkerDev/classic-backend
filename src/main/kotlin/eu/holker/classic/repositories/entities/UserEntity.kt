package eu.holker.classic.repositories.entities

import javax.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    var email: String,
    var password: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0
}