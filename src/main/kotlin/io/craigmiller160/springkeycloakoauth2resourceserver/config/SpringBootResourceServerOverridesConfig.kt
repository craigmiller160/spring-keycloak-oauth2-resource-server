package io.craigmiller160.springkeycloakoauth2resourceserver.config

import java.util.Properties
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.PropertiesPropertySource

@Configuration
class SpringBootResourceServerOverridesConfig(
    private val keycloakResourceServerConfig: KeycloakResourceServerConfig
) : ApplicationListener<ApplicationEnvironmentPreparedEvent> {
  override fun onApplicationEvent(event: ApplicationEnvironmentPreparedEvent) {
    Properties()
        .apply {
          put(
              "spring.security.oauth2.resourceserver.jwt.issuer-uri",
              keycloakResourceServerConfig.issuerUri)
          put(
              "spring.security.oauth2.resourceserver.jwt.jwt-set-uri",
              keycloakResourceServerConfig.jwkSetUri)
        }
        .let { props -> PropertiesPropertySource("keycloak-props", props) }
        .let { propSource -> event.environment.propertySources.addFirst(propSource) }
  }
}
