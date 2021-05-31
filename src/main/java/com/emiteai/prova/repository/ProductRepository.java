package com.emiteai.prova.repository;

import com.emiteai.prova.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findTop5ByNameContainsIgnoreCase(String name);

//    List<Product> find

}
