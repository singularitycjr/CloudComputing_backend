package com.example.cloudcomputing2023.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("payment")
public class Payment {

    @TableId(value = "paymentID", type = IdType.AUTO)
    private Integer paymentID;

    private Integer orderId;

    private BigDecimal amount;

    private String paymentMethod;

    private Date paymentDate;
}
