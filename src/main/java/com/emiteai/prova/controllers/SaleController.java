package com.emiteai.prova.controllers;

import com.emiteai.prova.model.Sale;
import com.emiteai.prova.model.SaleSetup;
import com.emiteai.prova.repository.ProductRepository;
import com.emiteai.prova.repository.SaleRepository;
import com.emiteai.prova.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value = "/{id}")
    public Sale getSaleById(@PathVariable("id") int id ) {
        try {


            if (id == 0) return null;

            SaleService saleService = new SaleService(saleRepository, productRepository);
            return saleService.getSaleById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao pesquisar Pedido por ID", e);
        }
    }

    @GetMapping
    @RequestMapping("/get")
    public List<Sale> getSales(@RequestParam(name = "l", required = false) Integer limit) {
        try {
            SaleService saleService = new SaleService(saleRepository, productRepository);
            return saleService.getSales(limit);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erro ao pesquisar pedidos", e);
        }
    }

    @PostMapping
    public Sale createSale(@RequestBody SaleSetup saleSetup) {
        try {
            if (saleSetup == null) return null;

            SaleService saleService = new SaleService(saleRepository, productRepository);
            return saleService.createSale(saleSetup);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping
    @RequestMapping("/getReportSales")
    public List<Map<String, String>> getSalesReport() {
        try {
            SaleService saleService = new SaleService(saleRepository, productRepository);
            return saleService.getSalesReportData();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }


}
