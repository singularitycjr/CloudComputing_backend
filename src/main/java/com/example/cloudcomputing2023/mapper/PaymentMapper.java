package com.example.cloudcomputing2023.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cloudcomputing2023.pojo.Orders;
import com.example.cloudcomputing2023.pojo.Payment;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {
}