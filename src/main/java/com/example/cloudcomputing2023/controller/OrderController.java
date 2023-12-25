package com.example.cloudcomputing2023.controller;

import com.example.cloudcomputing2023.query.OrdersQuery;
import com.example.cloudcomputing2023.query.ProductQuery;
import com.example.cloudcomputing2023.query.StatisticsQuery;
import com.example.cloudcomputing2023.service.IOrdersService;
import com.example.cloudcomputing2023.service.IProductService;
import com.example.cloudcomputing2023.vo.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
@Slf4j
@Tag(name = "订单数据")
public class OrderController {
    @Autowired
    private IOrdersService ordersService;

    @GetMapping("/get")
    @Operation(summary = "获取订单信息")
    public ResponseResult get(OrdersQuery ordersQuery ) {
        return this.ordersService.get(ordersQuery);
    }


}