package com.emiteai.prova.controllers;

import com.emiteai.prova.model.Sale;
import com.emiteai.prova.model.SaleSetup;
import com.emiteai.prova.repository.ProductRepository;
import com.emiteai.prova.repository.SaleRepository;
import com.emiteai.prova.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value = "/{id}")
    public Sale getSaleById(@PathVariable("id") int id ) {
        if (id == 0) return null;

        SaleService saleService = new SaleService(saleRepository, productRepository);
        return saleService.getSaleById(id);
    }

    @GetMapping
    @RequestMapping("/get")
    public List<Sale> getSales(@RequestParam(name = "l", required = false) Integer limit) {
        try {
            SaleService saleService = new SaleService(saleRepository, productRepository);
            return saleService.getSales(limit);

        } catch (Exception e) {

            return null;
        }
    }

    @PostMapping
    public Sale createSale(@RequestBody SaleSetup saleSetup) {
        try {
            if (saleSetup == null) return null;

            SaleService saleService = new SaleService(saleRepository, productRepository);
            return saleService.createSale(saleSetup);
        } catch (Exception e) {
            return null;
        }
    }


}
