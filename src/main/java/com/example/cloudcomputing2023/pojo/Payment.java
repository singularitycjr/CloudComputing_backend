package com.example.cloudcomputing2023.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;

@Data
@TableName("payment")
public class Payment {

    @TableId(value = "paymentId", type = IdType.AUTO)
    private Integer paymentId;

    private Integer orderId;

    private BigDecimal amount;

    private String paymentMethod;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date paymentDate;
}
