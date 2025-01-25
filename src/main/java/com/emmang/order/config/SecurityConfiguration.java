package com.emmang.order.config;

import com.emmang.order.filter.AuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final AuthorizationFilter authorizationFilter;

    public SecurityConfiguration(AuthorizationFilter authorizationFilter) {
        this.authorizationFilter = authorizationFilter;
    }

    @Bean
    public SecurityFilterChain configure (HttpSecurity http) throws Exception {
        return http.csrf()
                .disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/order/guest-access-only").hasRole("GUEST")
                        .requestMatchers("/order/admin-access-only").hasRole("ADMIN")
                        .requestMatchers("/order/guest-access-call-guest-access").hasRole("GUEST")
                        .requestMatchers("/order/guest-access-call-admin-access").hasRole("GUEST")
                        .anyRequest().authenticated() // Authenticate all other requests
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT filter
                .build();
    }
}
