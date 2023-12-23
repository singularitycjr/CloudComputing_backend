package com.example.cloudcomputing2023.query;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductQuery extends PageQuery{
    public ProductQuery() {
        super();
    }
    private String productName;
    private Integer productId;
    private BigDecimal priceLowerBound;
    private BigDecimal priceUpperBound;

}
