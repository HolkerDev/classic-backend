package eu.holker.classic.security

import eu.holker.classic.repositories.UserRepository
import mu.KLogging
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class JwtUserService(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email)
        user?.let {
            return User(it.email, it.password, listOf()) //TODO: Add authorities later may be. May be not lol.
        }
        throw UsernameNotFoundException("User with username was not found")
    }

    companion object : KLogging()
}

fun UserDetailsService.loadUserByEmail(email: String): UserDetails = this.loadUserByUsername(email)