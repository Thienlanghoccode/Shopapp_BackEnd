package com.java.PTPMHDV13.Vinfast_Sales.config;

import com.java.PTPMHDV13.Vinfast_Sales.filter.PreFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final AuthenticationProvider authenticationProvider;

    private final PreFilter preFilter;

    private static final String[] SWAGGER_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/webjars/**",
            "/swagger-resources/configuration/ui",
            "/swagger-resources/configuration/security",
            "/swagger-ui.html/**",
            "/swagger-ui/**"
    };

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(SWAGGER_WHITELIST).permitAll()
                        .anyRequest().permitAll()
                )
                .sessionManagement(manager -> manager
                        .sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider).addFilterBefore(preFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
                        })
                )
        ;
        return http.build();
    }

}
