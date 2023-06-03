package io.craigmiller160.springkeycloakoauth2resourceserver.config

import java.util.Properties
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent
import org.springframework.context.ApplicationListener
import org.springframework.core.env.PropertiesPropertySource

class SpringBootResourceServerOverridesListener :
    ApplicationListener<ApplicationEnvironmentPreparedEvent> {
  private val log = LoggerFactory.getLogger(javaClass)
  override fun onApplicationEvent(event: ApplicationEnvironmentPreparedEvent) {
    val config = KeycloakResourceServerConfig.fromEnvironment(event.environment)
    log.debug("Resolved keycloak resource server config: $config")
    Properties()
        .apply {
          put("spring.security.oauth2.resourceserver.jwt.issuer-uri", config.issuerUri)
          put("spring.security.oauth2.resourceserver.jwt.jwt-set-uri", config.jwkSetUri)
        }
        .let { props -> PropertiesPropertySource("keycloak-props", props) }
        .let { propSource -> event.environment.propertySources.addFirst(propSource) }
  }
}
