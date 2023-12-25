package com.example.cloudcomputing2023.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class OrderDetailDTO {
    private Integer orderDetailId;
    private Integer orderId;
    private Integer productId;
    private String productName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal sumPrice;
}
