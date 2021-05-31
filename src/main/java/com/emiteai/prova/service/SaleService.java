package com.emiteai.prova.service;

import com.emiteai.prova.model.*;
import com.emiteai.prova.repository.ProductRepository;
import com.emiteai.prova.repository.SaleRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class SaleService {

    private SaleRepository saleRepository;

    private ProductRepository productRepository;


    public SaleService(SaleRepository saleRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }

    public Sale getSaleById(Integer id) {
        Objects.requireNonNull(id);

        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
        return sale;
    }

    public List<Sale> getSales(Integer limit) {
        if (limit == null) limit = 10;
        List<Sale> result = saleRepository.findAll(PageRequest.of(0,limit)).getContent();
        return result;
    }

    public Sale createSale(SaleSetup saleSetup) throws Exception {
        Objects.requireNonNull(saleSetup);

          Sale newSale = mountSaleFromSetup(saleSetup);
          if (saleSetup.getTransportOrder() != null) {
              mountNewTransportOrder(saleSetup.getTransportOrder(), newSale);
          }
          Sale savedSale = saleRepository.save(newSale);

          return savedSale;
    }

    private void mountNewTransportOrder(TransportOrder transportOrder, Sale sale) {
        if (transportOrder.getInfos() == null) return;
        transportOrder.setSale(sale);
        sale.setTransportOrder(transportOrder);
    }

    private Sale mountSaleFromSetup(SaleSetup saleSetup) throws Exception {
        Sale sale = new Sale();
        if (saleSetup.getProductsAmount() == null || saleSetup.getProductsAmount().isEmpty()) {
            throw new Exception("Adicione ao menos um item ao pedido!");
        }

        ProductService productService = new ProductService(productRepository);
        for (Map<String, Integer> productEntry : saleSetup.getProductsAmount()) {
            Integer productId = productEntry.get("productId");
            Integer amount = productEntry.get("amount");

            if (productId == null || productId == 0 || amount == null || amount == 0) continue;

            Product product = productService.getProductById(productId);
            if (product == null) continue;

            SaleProduct saleProduct = new SaleProduct();
            saleProduct.setProduct(product);
            saleProduct.setAmount(amount);
            saleProduct.setTotalValue(product.getUnitaryPrice() * amount);
            sale.addProduct(saleProduct);

        }

        calculateSaleTotalValue(sale);
        validateNewSale(sale);
        return sale;
    }

    private void validateNewSale(Sale sale) throws Exception {
        Integer productsAmount = sale.getProductTotalAmount();
        if (productsAmount < 1 || productsAmount > 3) {
            throw new Exception("Pedido deve conter entre 1 e 3 produtos!");
        }
    }

    private void calculateSaleTotalValue(Sale sale) {
        Long totalValue = 0L;
        for (SaleProduct product : sale.getProducts()) {
            totalValue += product.getTotalValue();
        }

        sale.setSaleValue(totalValue);
    }
}
