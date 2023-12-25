package com.example.cloudcomputing2023.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("customer")
public class Customer {

    @TableId(value = "customerId", type = IdType.AUTO)
    private Integer customerId;

    private String customerName;

    private String phone;

    private String email;

    private String address;

}
