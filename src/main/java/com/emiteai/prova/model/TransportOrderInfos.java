package com.emiteai.prova.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransportOrderInfos {

    private String clientName;
    private String document;
    private String addressName;
    private String addressNumber;
    private String addressCity;
    private String addressState;
    private String addressZipCode;
}
