package io.craigmiller160.springkeycloakoauth2resourceserver.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.security.keycloak.oauth2.resourceserver")
data class KeycloakResourceServerConfig(val host: String, val realm: String, val clientId: String)
