# spring-keycloak-oauth2-resource-server

A wrapper around the necessary code to setup a Spring Boot OAuth2 Resource Server with my Keycloak Authorization Server.

## How to Use

The library uses spring auto-configuration to setup the consuming project. However, there are some requirements still:

First, setup the following properties to configure the resource server:

```yaml
spring:
  security:
    keycloak:
      oauth2:
        resourceserver:
          host: ###
          realm: ###
          client-id: ###
```

Then, update your web security config accordingly:

```kotlin
class WebSecurityConfig(
    private val keycloakOAuth2ResourceServerProvider: KeycloakOAuth2ResourceServerProvider
) {
    // For WebMVC
    @Bean
    fun securityFilterChain(http: HttpSecurity): DefaultSecurityFilterChain = 
        http.oauth2ResourceServer(keycloakOAuth2ResourceServerProvider.provideWebMvc())
            .build()
    
    // For WebFlux
    @Bean
    fun securityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain = 
        http.oauth2ResourceServer(keycloakOAuth2ResourceServerProvider.provideWebFlux())
            .build()
}
```