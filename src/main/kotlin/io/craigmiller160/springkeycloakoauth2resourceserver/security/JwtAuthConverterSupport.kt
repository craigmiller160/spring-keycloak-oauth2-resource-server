package io.craigmiller160.springkeycloakoauth2resourceserver.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt

internal fun getRoles(jwt: Jwt, clientId: String): Collection<GrantedAuthority> =
    jwt.getClaim<Map<String, Map<String, Collection<String>>>>("resource_access")
        .entries
        .firstOrNull { (key) -> key == clientId }
        ?.value
        ?.get("roles")
        ?.map { role -> SimpleGrantedAuthority("ROLE_$role") }
        ?.toSet()
        ?: setOf()
