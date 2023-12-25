package com.example.cloudcomputing2023.controller;


import com.example.cloudcomputing2023.query.OrdersQuery;
import com.example.cloudcomputing2023.query.StatisticsQuery;
import com.example.cloudcomputing2023.service.IOrderDetailService;
import com.example.cloudcomputing2023.service.IOrdersService;
import com.example.cloudcomputing2023.vo.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderDetail")
@Slf4j
@Tag(name = "订单详细数据")
public class OrderDetailController {

    @Autowired
    private IOrderDetailService orderDetailService;

    @GetMapping("/getById/{id}")
    @Operation(summary = "获取订单详细信息")
    public ResponseResult getById(@PathVariable("id") Integer id ) {
        return this.orderDetailService.getById(id);
    }

    @GetMapping("/getStatistics")
    @Operation(summary = "获取销售统计信息")
    public ResponseResult getStatistics(StatisticsQuery statisticsQuery ) {
        return this.orderDetailService.getStatistics(statisticsQuery);
    }
}
