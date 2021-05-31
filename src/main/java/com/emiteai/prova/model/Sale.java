package com.emiteai.prova.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Sale")
@Table(name = "sales")
public class Sale extends GenericEntity {

    @JsonManagedReference
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleProduct> products = new ArrayList<>();

    private Long saleValue;

    public Integer getProductTotalAmount() {
        Integer amount = 0;
        for (SaleProduct product : products) {
            amount += product.getAmount();
        }
        return amount;
    }

    public void addProduct(SaleProduct saleProduct) {
        saleProduct.setSale(this);
        products.add(saleProduct);
    }

}
