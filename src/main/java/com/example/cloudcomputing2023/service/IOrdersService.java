package com.example.cloudcomputing2023.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.cloudcomputing2023.pojo.Orders;
import com.example.cloudcomputing2023.query.OrdersQuery;
import com.example.cloudcomputing2023.query.StatisticsQuery;
import com.example.cloudcomputing2023.vo.ResponseResult;

public interface IOrdersService extends IService<Orders> {
    ResponseResult get(OrdersQuery ordersQuery );


}
