package com.example.cloudcomputing2023.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("orders")
public class Orders {
    @TableId(value = "orderId", type = IdType.AUTO)
    private Integer orderId;

    private Integer customerId;

    private Date orderDate;
}
