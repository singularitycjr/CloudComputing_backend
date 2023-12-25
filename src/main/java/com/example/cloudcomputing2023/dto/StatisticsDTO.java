package com.example.cloudcomputing2023.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StatisticsDTO {
    private Integer productId;
    private String productName;
    private Integer saleQuantity;
    private BigDecimal saleAmount;

    public StatisticsDTO(){
        productId = 0;
        productName = "";
        saleQuantity = 0;
        saleAmount = new BigDecimal(0);

    }

}
