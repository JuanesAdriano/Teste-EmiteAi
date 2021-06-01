package com.emiteai.prova.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@NoArgsConstructor
@Entity
public class SaleCodeSequenceNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long code;
}
