package eu.holker.classic.controllers

import eu.holker.classic.controllers.forms.LoginForm
import eu.holker.classic.controllers.responses.TokenResponse
import eu.holker.classic.utils.JwtUtils
import mu.KLogging
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val authManager: AuthenticationManager, private val jwtUtils: JwtUtils) {

    @PostMapping("/login")
    fun login(@RequestBody loginForm: LoginForm): ResponseEntity<*> {
        val auth = authManager.authenticate(UsernamePasswordAuthenticationToken(loginForm.email, loginForm.password))
        SecurityContextHolder.getContext().authentication = auth
        val token = jwtUtils.generateJwtToken(auth)
        return ResponseEntity.ok(TokenResponse(token))
    }

    companion object : KLogging()
}