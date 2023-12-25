package com.example.cloudcomputing2023.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;

@Data
public class OrdersQuery extends  PageQuery {
    public OrdersQuery(){
        super();
    }
    private Integer orderId;
    private Integer customerId;
    private String customerName;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date orderDate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date  startTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date  endTime;
}
