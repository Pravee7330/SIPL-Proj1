/*
 * package com.sipl.yard.management.config;
 * 
 * import java.util.Arrays;
 * 
 * import org.keycloak.adapters.KeycloakConfigResolver; import
 * org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver; import
 * org.keycloak.adapters.springsecurity.KeycloakConfiguration; import
 * org.keycloak.adapters.springsecurity.authentication.
 * KeycloakAuthenticationProvider; import
 * org.keycloak.adapters.springsecurity.config.
 * KeycloakWebSecurityConfigurerAdapter; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.method.configuration.
 * EnableGlobalMethodSecurity; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
 * import org.springframework.security.core.session.SessionRegistryImpl; import
 * org.springframework.security.web.authentication.session.
 * RegisterSessionAuthenticationStrategy; import
 * org.springframework.security.web.authentication.session.
 * SessionAuthenticationStrategy; import
 * org.springframework.web.cors.CorsConfiguration; import
 * org.springframework.web.cors.CorsConfigurationSource; import
 * org.springframework.web.cors.UrlBasedCorsConfigurationSource;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity
 * 
 * @EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
 * 
 * @KeycloakConfiguration public class KeycloakSecurityConfig extends
 * KeycloakWebSecurityConfigurerAdapter {
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * super.configure(http); http.authorizeRequests().anyRequest().authenticated();
 * http.csrf().disable(); http.cors(); }
 * 
 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
 * throws Exception { KeycloakAuthenticationProvider
 * keycloakAuthenticationProvider = keycloakAuthenticationProvider();
 * keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new
 * SimpleAuthorityMapper());
 * auth.authenticationProvider(keycloakAuthenticationProvider); }
 * 
 * @Bean
 * 
 * @Override protected SessionAuthenticationStrategy
 * sessionAuthenticationStrategy() { return new
 * RegisterSessionAuthenticationStrategy(new SessionRegistryImpl()); }
 * 
 * @Bean public KeycloakConfigResolver keycloakConfigResolver() { return new
 * KeycloakSpringBootConfigResolver(); }
 * 
 * @Bean CorsConfigurationSource corsConfigurationSource() { CorsConfiguration
 * configuration = new CorsConfiguration();
 * configuration.setAllowedOrigins(Arrays.asList("*")); configuration
 * .setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT",
 * "PATCH")); configuration.setAllowedHeaders(Arrays .asList("X-Requested-With",
 * "Origin", "Content-Type", "Accept", "Authorization"));
 * configuration.setAllowCredentials(Boolean.valueOf(true));
 * UrlBasedCorsConfigurationSource source = new
 * UrlBasedCorsConfigurationSource(); source.registerCorsConfiguration("/**",
 * configuration); return source; } }
 */
