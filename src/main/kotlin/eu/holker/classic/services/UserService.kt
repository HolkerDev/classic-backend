package eu.holker.classic.services

import eu.holker.classic.repositories.UserRepository
import eu.holker.classic.repositories.entities.UserEntity
import eu.holker.classic.repositories.entities.dto
import eu.holker.classic.services.dto.UserDto
import mu.KLogging
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {

    fun createUser(email: String, password: String): UserDto {
        val user = userRepository.save(UserEntity(email, passwordEncoder.encode(password)))
        return user.dto
    }

    fun findByUserId(userId: Int): Result<UserDto> {
        val user = userRepository.findById(userId)
        if (user.isPresent) {
            return Result.success(user.get().dto)
        }else{
            return Result.failure(Exception("User with such id was not found"))
        }
    }

    companion object : KLogging()
}
