package com.emiteai.prova.controllers;

import com.emiteai.prova.model.Product;
import com.emiteai.prova.repository.ProductRepository;
import com.emiteai.prova.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado por ID", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao consultar Produto por ID", e);
        }
    }

    @GetMapping
    @RequestMapping("/query")
    public List<Product> getProducts(@RequestParam(name = "name", required = false) String name) {
        try {
            ProductService service = new ProductService(productRepository);
            return service.getProducts(name);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao consultar Produtos", e);
        }
    }



}
