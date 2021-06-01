package com.emiteai.prova.repository;

import com.emiteai.prova.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

    public List<Sale> findAllBySaleValueGreaterThanEqual(Long saleValue);
}
