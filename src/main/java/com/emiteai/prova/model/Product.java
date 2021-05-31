package com.emiteai.prova.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Product")
@Table(name = "products")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Product extends GenericEntity{

    @Column(length = 85)
    private String name;

    @Column(name = "unitary_price")
    private Long unitaryPrice;

    public Long getUnitaryPrice() {
        return unitaryPrice != null ? unitaryPrice : 0L;
    }
}
