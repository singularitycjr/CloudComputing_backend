package com.example.cloudcomputing2023.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;

@Data
public class OrderDTO {
    private Integer orderId;
    private Integer customerId;
    private String customerName;
    private Date orderDate;
    private Date  paymentDate;
    private String paymentMethod;
}
