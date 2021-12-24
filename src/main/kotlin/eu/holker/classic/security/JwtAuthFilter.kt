package eu.holker.classic.security

import eu.holker.classic.utils.JwtUtils
import eu.holker.classic.utils.jwtToken
import mu.KLogging
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthFilter(private val userDetailsService: UserDetailsService, private val jwtUtils: JwtUtils) :
    OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token: String? = request.jwtToken
        if (token != null && jwtUtils.validateJwtToken(token)) {
            jwtUtils.getEmailFromJwtToken(token)?.let { email ->
                val userDetails = userDetailsService.loadUserByEmail(email)
                val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        }
        filterChain.doFilter(request, response)
    }

    companion object : KLogging()
}