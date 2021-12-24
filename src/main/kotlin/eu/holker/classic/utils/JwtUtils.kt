package eu.holker.classic.utils

import io.jsonwebtoken.*
import mu.KLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.util.Date


@Component
class JwtUtils {

    @Value("\${app.jwt.secret}")
    lateinit var secret: String

    val expiration: Long = 432200000 // 8 days

    fun getEmailFromJwtToken(token: String?): String? {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .body
            .subject
    }

    fun generateJwtToken(authentication: Authentication): String {
        val userPrincipal = authentication.principal as User
        return Jwts.builder()
            .setSubject(userPrincipal.username)
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + expiration))
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact()
    }

    fun validateJwtToken(authToken: String?): Boolean {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken)
            return true
        } catch (e: SignatureException) {
            logger.error("Invalid JWT signature: {}", e.message)
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token: {}", e.message)
        } catch (e: ExpiredJwtException) {
            logger.error("JWT token is expired: {}", e.message)
        } catch (e: UnsupportedJwtException) {
            logger.error("JWT token is unsupported: {}", e.message)
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty: {}", e.message)
        } catch (e: NullPointerException) {
            logger.error { "JWT token is null" }
        }
        return false
    }

    companion object : KLogging()
}