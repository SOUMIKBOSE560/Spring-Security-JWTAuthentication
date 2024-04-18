package com.example.springsecurityjwt.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        List<GrantedAuthority> authorities = new ArrayList<>();
//
//        //CREATE AUTHORITIES
//        GrantedAuthority roleUser = new SimpleGrantedAuthority("ROLE_USER");
//        GrantedAuthority roleAdmin = new SimpleGrantedAuthority(("ROLE_ADMIN"));
//        GrantedAuthority permissionRead = new SimpleGrantedAuthority("PERMISSION_READ");
//
//        authorities.add(roleUser);authorities.add(roleAdmin);authorities.add(permissionRead);
//
//        User user = (User) User.builder()
//                .username("user")
//                .password(encoder().encode("user"))
//                .authorities(authorities)
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        return security.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(
                request -> request.requestMatchers("/admin/**").permitAll()
        ).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}


