package com.crio.video_rental_system.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(requests -> requests

                        .requestMatchers(HttpMethod.POST, "/api/public/**").permitAll()

                        .requestMatchers("/api/private/videos")
                        .hasAnyRole("CUSTOMER", "ADMIN")

                        .requestMatchers("/api/private/videos/**")
                        .hasRole("ADMIN")


                .anyRequest().authenticated()

        ).httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails customer = User
                .withUsername("customer")
                .password(passwordEncoder().encode("customer@123"))
                .roles("CUSTOMER").build();

        UserDetails admin = User
                .withUsername("admin")
                .password(passwordEncoder().encode("admin@123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(customer, admin);
    }

}
