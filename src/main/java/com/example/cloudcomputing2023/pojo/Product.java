package com.example.cloudcomputing2023.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    @TableId(value = "productId", type = IdType.AUTO)
    private Integer productId;

    private String productName;

    private BigDecimal unitPrice;


}
