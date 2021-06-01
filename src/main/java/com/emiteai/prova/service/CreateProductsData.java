package com.emiteai.prova.service;

import com.emiteai.prova.model.Product;
import com.emiteai.prova.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreateProductsData {

    @Autowired
    private ProductRepository productRepository;

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        event.getApplicationContext().getBean(CreateProductsData.class).insertNonExistentProducts();
    }

    @Transactional
    public void insertNonExistentProducts() {
        Page<Product> result = productRepository.findAll(PageRequest.of(0, 1));
        if (result.getContent().isEmpty()) {
            List<Product> plainProducts = new ArrayList<>();
            plainProducts.add(new Product("Televisão 50Pol", 120000L));
            plainProducts.add(new Product("Speaker JBL", 40000L));
            plainProducts.add(new Product("Fones Bluetooth", 20000L));
            plainProducts.add(new Product("Alexa EchoDot", 60000L));

            productRepository.saveAll(plainProducts);
            System.out.println("Produtos Criados!");
        }
    }
}
