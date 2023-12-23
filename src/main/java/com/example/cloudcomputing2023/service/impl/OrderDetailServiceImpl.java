package com.example.cloudcomputing2023.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.cloudcomputing2023.mapper.OrderDetailMapper;
import com.example.cloudcomputing2023.pojo.OrderDetail;
import com.example.cloudcomputing2023.service.IOrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {
}
