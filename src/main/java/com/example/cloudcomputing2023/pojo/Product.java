package com.example.cloudcomputing2023.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("product")
public class Product {
    @TableId(value = "productId", type = IdType.AUTO)
    private Integer productId;

    private String productName;

    private BigDecimal unitPrice;


}
