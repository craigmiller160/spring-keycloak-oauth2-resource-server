package io.craigmiller160.springkeycloakoauth2resourceserver.security

import io.craigmiller160.springkeycloakoauth2resourceserver.config.KeycloakResourceServerConfig
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken

class WebMvcJwtAuthConverter(private val config: KeycloakResourceServerConfig) :
    Converter<Jwt, AbstractAuthenticationToken> {
  override fun convert(jwt: Jwt): AbstractAuthenticationToken {
    val principal = jwt.getClaim<String>(config.principalAttribute)
    return JwtAuthenticationToken(jwt, getRoles(jwt, config.clientId), principal)
  }
}
