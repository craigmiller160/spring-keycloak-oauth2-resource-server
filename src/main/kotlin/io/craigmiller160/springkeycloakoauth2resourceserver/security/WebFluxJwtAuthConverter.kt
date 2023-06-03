package io.craigmiller160.springkeycloakoauth2resourceserver.security

import io.craigmiller160.springkeycloakoauth2resourceserver.config.KeycloakResourceServerConfig
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import reactor.core.publisher.Mono

class WebFluxJwtAuthConverter(private val config: KeycloakResourceServerConfig) :
    Converter<Jwt, Mono<AbstractAuthenticationToken>> {
  override fun convert(jwt: Jwt): Mono<AbstractAuthenticationToken> {
    val principal = jwt.getClaim<String>(config.principalAttribute)
    return JwtAuthenticationToken(jwt, getRoles(jwt, config.clientId), principal).let {
      Mono.just(it)
    }
  }
}
