package com.emiteai.prova.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "SellProduct")
@Table(name = "sale_products")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class SaleProduct extends GenericEntity {



    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference

    private Sale sale;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column
    private Integer amount;

    @Column
    private Long totalValue;

}
