package com.example.cloudcomputing2023.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.cloudcomputing2023.mapper.OrdersMapper;
import com.example.cloudcomputing2023.pojo.Orders;
import com.example.cloudcomputing2023.service.IOrdersService;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {
}
