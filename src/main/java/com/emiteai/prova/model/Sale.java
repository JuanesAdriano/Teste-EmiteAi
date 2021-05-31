package com.emiteai.prova.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Sale")
@Table(name = "sales")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Sale extends GenericEntity {

    @JsonManagedReference
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleProduct> products = new ArrayList<>();

    private Long saleValue;

    @Column(name = "code", columnDefinition = "serial")
    private Long code;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_order_id", referencedColumnName = "id")
    private TransportOrder transportOrder;

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
