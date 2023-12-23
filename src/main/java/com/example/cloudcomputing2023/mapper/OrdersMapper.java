package com.example.cloudcomputing2023.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cloudcomputing2023.pojo.OrderDetail;
import com.example.cloudcomputing2023.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
}