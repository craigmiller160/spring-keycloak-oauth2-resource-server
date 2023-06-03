package io.craigmiller160.springkeycloakoauth2resourceserver.security

import io.craigmiller160.springkeycloakoauth2resourceserver.config.KeycloakResourceServerConfig
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.context.annotation.Bean
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class KeycloakOAuth2Customizers(private val config: KeycloakResourceServerConfig) {
  @ConditionalOnClass(Mono::class)
  @Bean("webfluxKeycloakOAuth2ResourceServer")
  fun webfluxKeycloakOAuth2ResourceServer():
      Customizer<ServerHttpSecurity.OAuth2ResourceServerSpec> =
      Customizer<ServerHttpSecurity.OAuth2ResourceServerSpec> { spec ->
        spec.jwt { jwt -> jwt.jwtAuthenticationConverter(WebFluxJwtAuthConverter(config)) }
      }

  @Bean("webmvcKeycloakOAuth2ResourceServer")
  fun webmvcKeycloakOAuth2ResourceServer():
      Customizer<OAuth2ResourceServerConfigurer<HttpSecurity>> =
      Customizer<OAuth2ResourceServerConfigurer<HttpSecurity>> { spec ->
        spec.jwt { jwt -> jwt.jwtAuthenticationConverter(WebMvcJwtAuthConverter(config)) }
      }
}
