package com.example.springsecurityjwt.controllers;

import com.example.springsecurityjwt.configs.JwtHelper;
import com.example.springsecurityjwt.models.JwtRequest;
import com.example.springsecurityjwt.models.Product;
import com.example.springsecurityjwt.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private ProductService productService;

    @GetMapping("/test")
    public String message(){
        return "ADMIN CONTROLLER TEST";
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody JwtRequest request){

        System.out.println(request);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        if(authentication.isAuthenticated()){
            System.out.println(authentication.getPrincipal());
            System.out.println(authentication.getAuthorities());
            return jwtHelper.generateToken(request.getUsername());
        }
        return "USER IS NOT AUTHENTICATED";
    }

    @GetMapping("/all")
    public List<Product> all(@RequestHeader("Authorization") String authorizationHeader) throws NoSuchAlgorithmException {
        String token = authorizationHeader.substring(7);
        System.out.println("TOKEN : " + token);
        boolean value = jwtHelper.validateToken(token);
        if(value) {
            return productService.getAllProducts();
        }
        return null;

    }
}
