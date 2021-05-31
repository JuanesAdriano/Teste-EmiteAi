package com.emiteai.prova.controllers;

import com.emiteai.prova.model.Product;
import com.emiteai.prova.repository.ProductRepository;
import com.emiteai.prova.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value = "/{id}")
    public Product getProductById(@PathVariable("id") int id){
        try {
            ProductService service = new ProductService(productRepository);
            return service.getProductById(id);
        } catch (EntityNotFoundException e) {
            return null;
        }

    }

    @GetMapping
    @RequestMapping("/query")
    public List<Product> getProducts(@RequestParam(name = "name", required = false) String name) {
        try {
            ProductService service = new ProductService(productRepository);
            return service.getProducts(name);
        } catch (Exception e) {
            return null;
        }
    }



}
