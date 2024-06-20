package org.learnspringsecurity.springsecurity;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Configuration
public class JwtSecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Require authentication for all requests
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

        // Disable session creation as the application is stateless
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Enable HTTP Basic authentication
        http.httpBasic();

        // Disable CSRF protection as it is not needed for stateless REST APIs
        http.csrf().disable();

        http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

        http.headers().frameOptions().sameOrigin();

        // Build and return the SecurityFilterChain
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService()
    {
        var user = User.withUsername("Jashan")
//                .password("{noop}dummy")
                .password("dummy")
                .passwordEncoder(str-> passwordEncoder().encode(str))
                .roles("USER")
                .build();
        var admin = User.withUsername("admin")
//                .password("{noop}dummy")
                .password("jashanbhatia")
                .passwordEncoder(str->passwordEncoder().encode(str))
                .roles("USER", "ADMIN")
                .build();
        //create a new user details manager which corresponds to the datasource here h2 which is made by embedded database builder
        var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource());
        //create the user and admin using that manager
        jdbcUserDetailsManager.createUser(user);
        jdbcUserDetailsManager.createUser(admin);
        //return that manager
        return jdbcUserDetailsManager;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DataSource dataSource()
    {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION).build();
    }
    @Bean
    public KeyPair keyPair() {
        try {
            var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("RSA algorithm is not available", e);
        }
    }
    @Bean
    public RSAKey rsaKey(KeyPair keyPair)
    {
        return new RSAKey.Builder((RSAPublicKey)keyPair.getPublic()).privateKey(keyPair().getPrivate()).keyID(UUID.randomUUID().toString()).build();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey)
    {
        var JWKSet = new JWKSet(rsaKey);

        return (jwkSelector, securityContext) -> jwkSelector.select(JWKSet);

    }

    @Bean
    public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException
    {
        return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey ()).build();


    }

    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource)
    {
        return new NimbusJwtEncoder(jwkSource);

    }

}
