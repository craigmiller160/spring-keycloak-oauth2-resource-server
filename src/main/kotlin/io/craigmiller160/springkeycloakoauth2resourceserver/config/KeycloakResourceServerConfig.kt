package io.craigmiller160.springkeycloakoauth2resourceserver.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.core.env.Environment

@ConfigurationProperties(prefix = "spring.security.keycloak.oauth2.resourceserver")
data class KeycloakResourceServerConfig(val host: String, val realm: String, val clientId: String) {
  companion object {
    fun fromEnvironment(environment: Environment): KeycloakResourceServerConfig =
        KeycloakResourceServerConfig(
            host = environment.getProperty("spring.security.keycloak.oauth2.resourceserver.host")
                    ?: "",
            realm = environment.getProperty("spring.security.keycloak.oauth2.resourceserver.realm")
                    ?: "",
            clientId =
                environment.getProperty("spring.security.keycloak.oauth2.resourceserver.client-id")
                    ?: "")
  }
  val issuerUri = "$host/realms/$realm"
  val jwkSetUri = "$issuerUri/protocol/openid-connect/certs"
  val principalAttribute = "preferred_username"
}
