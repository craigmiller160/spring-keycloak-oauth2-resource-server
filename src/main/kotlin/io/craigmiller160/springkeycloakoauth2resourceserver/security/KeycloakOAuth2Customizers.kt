package io.craigmiller160.springkeycloakoauth2resourceserver.security

import io.craigmiller160.springkeycloakoauth2resourceserver.config.KeycloakResourceServerConfig
import org.springframework.context.annotation.Bean
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.stereotype.Component

@Component
class KeycloakOAuth2Customizers(private val resourceServerConfig: KeycloakResourceServerConfig) {
  @Bean("webfluxKeycloakOAuth2ResourceServer")
  fun webfluxKeycloakOAuth2ResourceServer():
      Customizer<ServerHttpSecurity.OAuth2ResourceServerSpec> =
      Customizer<ServerHttpSecurity.OAuth2ResourceServerSpec> { spec -> spec.jwt { jwt -> } }

  @Bean("webmvcKeycloakOAuth2ResourceServer")
  fun webmvcKeycloakOAuth2ResourceServer():
      Customizer<OAuth2ResourceServerConfigurer<HttpSecurity>> {
    TODO()
  }
}
