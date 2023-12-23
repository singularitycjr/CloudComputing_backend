package com.example.cloudcomputing2023.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("orderDetail")
public class OrderDetail {
    @TableId(value = "orderDetailID", type = IdType.AUTO)
    private Integer orderDetailID;

    private Integer orderId;

    private Integer productId;

    private Integer quantity;

    private BigDecimal unitPrice;
}
