package com.emiteai.prova.controllers;

import com.emiteai.prova.model.TransportOrder;
import com.emiteai.prova.repository.TransportOrderRepository;
import com.emiteai.prova.service.TransportOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/transportorder")
public class TransportOrderController {

    @Autowired
    private TransportOrderRepository transportOrderRepository;

    @GetMapping(value = "/{id}")
    public TransportOrder getTransportOrderById(@PathVariable("id") int id) {
        try {
            TransportOrderService transportOrderService = new TransportOrderService(transportOrderRepository);
            return transportOrderService.getTransportOrderById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Não encontrado nenhuma ordem de transporte pelo ID " + String.valueOf(id), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erro ao consultar Ordem de Transporte: " + e.getMessage(), e);
        }

    }

    @GetMapping
    @RequestMapping("/get")
    public List<TransportOrder> getTransportOrders() {
        try {
            TransportOrderService transportOrderService = new TransportOrderService(transportOrderRepository);
            return transportOrderService.getTransportOrders();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erro ao consultar Ordens de Transporte:" + e.getMessage(), e);
        }
    }

}
