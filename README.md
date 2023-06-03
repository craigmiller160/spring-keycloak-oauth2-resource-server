# spring-keycloak-oauth2-resource-server

A wrapper around the necessary code to setup a Spring Boot OAuth2 Resource Server with my Keycloak Authorization Server.

## How to Use

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