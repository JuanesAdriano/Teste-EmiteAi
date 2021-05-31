package com.emiteai.prova.service;

import com.emiteai.prova.model.Product;
import com.emiteai.prova.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product getProductById(Integer productId) {
        Objects.requireNonNull(productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(productId.toString()));

        return product;
    }

    @Transactional
    public List<Product> getProducts(String name) {
        if (name != null && !name.isBlank()) {
            return productRepository.findTop5ByNameContainsIgnoreCase(name.trim());
        }
        Page<Product> productsPage =  productRepository.findAll(PageRequest.of(0, 5));
        return productsPage.getContent();
    }
}
