package com.emiteai.prova.service;

import com.emiteai.prova.model.TransportOrder;
import com.emiteai.prova.repository.TransportOrderRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class TransportOrderService {

    private TransportOrderRepository transportOrderRepository;

    public TransportOrderService(TransportOrderRepository transportOrderRepository) {
        this.transportOrderRepository = transportOrderRepository;
    }

    @Transactional
    public TransportOrder getTransportOrderById(Integer id) {
        Objects.requireNonNull(id);


        TransportOrder transportOrder = transportOrderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
        transportOrder.getSale().getSaleValue();

        return transportOrder;
    }

    public List<TransportOrder> getTransportOrders() {
        return transportOrderRepository.findAll();
    }
}
