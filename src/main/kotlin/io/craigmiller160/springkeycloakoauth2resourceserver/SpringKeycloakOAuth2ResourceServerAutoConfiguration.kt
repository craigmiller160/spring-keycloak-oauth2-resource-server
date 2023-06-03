package io.craigmiller160.springkeycloakoauth2resourceserver

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["io.craigmiller160.springkeycloakoauth2resourceserver"])
@ConfigurationPropertiesScan(
    basePackages = ["io.craigmiller160.springkeycloakoauth2resourceserver"])
class SpringKeycloakOAuth2ResourceServerAutoConfiguration
