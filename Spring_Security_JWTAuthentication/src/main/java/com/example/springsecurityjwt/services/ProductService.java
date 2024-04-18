package com.example.springsecurityjwt.services;

import com.example.springsecurityjwt.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class ProductService {
    public List<Product> getAllProducts(){
        Random random = new Random();
        List<Product> products = new ArrayList<>();
        try{
            for (int i = 0; i < 100; i++) {
               products.add(new Product(UUID.randomUUID().toString(),"PRODUCT : " + i, random.nextInt(1000)));
            }
            return products;
        }
        catch (Exception e){
            return null;
        }
    }
}
