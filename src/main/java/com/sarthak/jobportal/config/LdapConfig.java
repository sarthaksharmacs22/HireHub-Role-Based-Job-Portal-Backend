package com.sarthak.jobportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;

@Configuration
public class LdapConfig {

    @Bean
    public AuthenticationManager authenticationManager(
            BaseLdapPathContextSource contextSource) {

        LdapBindAuthenticationManagerFactory factory =
                new LdapBindAuthenticationManagerFactory(contextSource);

        factory.setUserDnPatterns("uid={0},ou=users");

        return factory.createAuthenticationManager();
    }
}