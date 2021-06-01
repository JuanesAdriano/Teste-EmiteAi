package com.emiteai.prova.repository;

import com.emiteai.prova.model.TransportOrder;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransportOrderRepository extends JpaRepository<TransportOrder, Integer> {
}
