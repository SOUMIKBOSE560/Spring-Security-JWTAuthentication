package com.example.springsecurityjwt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder encoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("TRYING USER : " + username);
        if(username.equals("Soumik")){

            List<GrantedAuthority> authorities = new ArrayList<>();

            //CREATE AUTHORITIES
            GrantedAuthority roleUser = new SimpleGrantedAuthority("ROLE_USER");
            GrantedAuthority roleAdmin = new SimpleGrantedAuthority(("ROLE_ADMIN"));
            GrantedAuthority permissionRead = new SimpleGrantedAuthority("PERMISSION_READ");
            authorities.add(roleUser);authorities.add(roleAdmin);authorities.add(permissionRead);

            return User.builder()
                    .username("Soumik")
                    .password(encoder.encode("Soumik@1998"))
                    .authorities(authorities)
                    .build();

        }
        throw new UsernameNotFoundException("USER NOT FOUND");
    }
}
