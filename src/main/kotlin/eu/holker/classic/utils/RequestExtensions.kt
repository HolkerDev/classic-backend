package eu.holker.classic.utils

import org.springframework.http.HttpHeaders
import javax.servlet.http.HttpServletRequest


val HttpServletRequest.jwtToken: String?
    get() {
        val header = this.getHeader(HttpHeaders.AUTHORIZATION)
        return if (header.contains("Bearer ")) header.substringAfter("Bearer ") else null
    }