package com.emiteai.prova.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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

    @OneToOne(cascade = CascadeType.ALL)
    private SaleCodeSequenceNumber code;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_order_id", referencedColumnName = "id")
    private TransportOrder transportOrder;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

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
