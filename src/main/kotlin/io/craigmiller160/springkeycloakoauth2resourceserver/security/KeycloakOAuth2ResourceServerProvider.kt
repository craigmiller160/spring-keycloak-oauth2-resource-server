package io.craigmiller160.springkeycloakoauth2resourceserver.security

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.stereotype.Component

@Component
class KeycloakOAuth2ResourceServerProvider(
    @Qualifier("webfluxKeycloakOAuth2ResourceServer")
    private val webFluxKeycloakOAuth2ResourceServer:
        Customizer<ServerHttpSecurity.OAuth2ResourceServerSpec>?,
    @Qualifier("webmvcKeycloakOAuth2ResourceServer")
    private val webmvcKeycloakOAuth2ResourceServer:
        Customizer<OAuth2ResourceServerConfigurer<HttpSecurity>>
) {
  fun provideWebFlux(): Customizer<ServerHttpSecurity.OAuth2ResourceServerSpec> {
    if (webFluxKeycloakOAuth2ResourceServer == null) {
      throw IllegalStateException(
          "No web flux customizer available, is Spring WebFlux on the classpath?")
    }
    return webFluxKeycloakOAuth2ResourceServer
  }

  fun provideWebMvc(): Customizer<OAuth2ResourceServerConfigurer<HttpSecurity>> =
      webmvcKeycloakOAuth2ResourceServer
}
