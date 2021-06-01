package com.emiteai.prova.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transport_orders")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class TransportOrder extends GenericEntity{

    @OneToOne(fetch = FetchType.LAZY)
    private Sale sale;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "infos")
    @Basic
    private TransportOrderInfos infos;


}
