package com.example.cloudcomputing2023.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.cloudcomputing2023.dto.OrderDTO;
import com.example.cloudcomputing2023.mapper.CustomerMapper;
import com.example.cloudcomputing2023.mapper.OrderDetailMapper;
import com.example.cloudcomputing2023.mapper.OrdersMapper;
import com.example.cloudcomputing2023.pojo.*;
import com.example.cloudcomputing2023.query.OrdersQuery;
import com.example.cloudcomputing2023.query.StatisticsQuery;
import com.example.cloudcomputing2023.service.IOrdersService;
import com.example.cloudcomputing2023.vo.PageVO;
import com.example.cloudcomputing2023.vo.ResponseResult;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {


    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private PaymentServiceImpl paymentService;

    @Override
    public ResponseResult get(OrdersQuery ordersQuery) {
//        JdbcTemplate jdbcTemplate=new JdbcTemplate();
        Integer pageNo = ordersQuery.getPageNo();
        Integer pageSize = ordersQuery.getPageSize();

        if(ordersQuery.getStartTime()==null){
            ordersQuery.setStartTime(java.sql.Date.valueOf("2000-01-01"));
        }
        if(ordersQuery.getEndTime()==null){
            ordersQuery.setEndTime(java.sql.Date.valueOf("2023-12-25"));
        }

        List<Customer> customerList=customerService.list(
                Wrappers.<Customer>lambdaQuery()
        );
        List<Payment> paymentList=paymentService.list(
                Wrappers.<Payment>lambdaQuery()
        );
//
////        MPJLambdaWrapper<Orders> wrapper = new MPJLambdaWrapper<Orders>()
////                .selectAll(Orders.class)
////                .leftJoin(Customer.class, Customer::getCustomerId, Orders::getCustomerId)
////                .eq(ObjectUtil.isAllNotEmpty(ordersQuery.getOrderId()), Orders::getOrderId, ordersQuery.getOrderId())
////                .eq(ObjectUtil.isAllNotEmpty(ordersQuery.getCustomerId()), Orders::getCustomerId, ordersQuery.getCustomerId())
////                .like(StrUtil.isNotBlank(ordersQuery.getCustomerName()), Customer::getCustomerName, ordersQuery.getCustomerName());
//////                .between(Orders::getOrderDate, ordersQuery.getStartTime(), ordersQuery.getEndTime());
////
////        String sql="SELECT     t.orderId,t.customerId,t.orderDate   FROM    orders t      LEFT JOIN customer t1 ON (t1.customerId = t.customerId)";
////
//
////        MPJLambdaWrapper<Orders> wrapper = new MPJLambdaWrapper<Orders>()
////                .eq(ObjectUtil.isAllNotEmpty(ordersQuery.getOrderId()), Orders::getOrderId, ordersQuery.getOrderId())
////                .eq(ObjectUtil.isAllNotEmpty(ordersQuery.getCustomerId()), Orders::getCustomerId, ordersQuery.getCustomerId())
//////                .like(StrUtil.isNotBlank(ordersQuery.getCustomerName()), Customer::getCustomerName, ordersQuery.getCustomerName());
////                .between(Orders::getOrderDate, ordersQuery.getStartTime(), ordersQuery.getEndTime());
////
//
        List<Orders> ordersList = this.list(
                Wrappers.<Orders>lambdaQuery()
                        .eq(ObjectUtil.isAllNotEmpty(ordersQuery.getOrderId()), Orders::getOrderId, ordersQuery.getOrderId())
                .eq(ObjectUtil.isAllNotEmpty(ordersQuery.getCustomerId()), Orders::getCustomerId, ordersQuery.getCustomerId())
//                .like(StrUtil.isNotBlank(ordersQuery.getCustomerName()), Customer::getCustomerName, ordersQuery.getCustomerName());
                .between(Orders::getOrderDate, ordersQuery.getStartTime(), ordersQuery.getEndTime())
        );



        if(StrUtil.isNotBlank(ordersQuery.getCustomerName()))
        {
            for(int i=0;i<ordersList.size();i++){
                Orders orders=ordersList.get(i);

//                Customer customer=customerService.getOne(
//                        Wrappers.<Customer>lambdaQuery().eq(Customer::getCustomerId, orders.getCustomerId())
//                );
                Customer customer = null;
                for (Customer c : customerList) {
                    if (c.getCustomerId().equals(orders.getCustomerId())) {
                        customer = c;
                        break;
                    }
                }
                if(!customer.getCustomerName().contains(ordersQuery.getCustomerName())) {
                    ordersList.remove(i);
                    i--;
                }
            }
        }

        List<OrderDTO> orderDTOList=new ArrayList<>();

        for (Orders orders : ordersList) {
//            Customer customer=customerService.getOne(
//                    Wrappers.<Customer>lambdaQuery().eq(Customer::getCustomerId, orders.getCustomerId())
//            );
            Customer customer = null;
            for (Customer c : customerList) {
                if (c.getCustomerId().equals(orders.getCustomerId())) {
                    customer = c;
                    break;
                }
            }
//            Payment payment=paymentService.getOne(
//                    Wrappers.<Payment>lambdaQuery().eq(Payment::getOrderId, orders.getOrderId())
//            );
            Payment payment = null;
            for (Payment p : paymentList) {
                if (p.getOrderId().equals(orders.getOrderId())) {
                    payment = p;
                    break;
                }
            }

            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderId(orders.getOrderId());
            orderDTO.setCustomerId(orders.getCustomerId());
            orderDTO.setOrderDate(orders.getOrderDate());
            orderDTO.setCustomerName(customer.getCustomerName());
            orderDTO.setPaymentDate(payment.getPaymentDate());
            orderDTO.setPaymentMethod(payment.getPaymentMethod());
            orderDTOList.add(orderDTO);
        }


        PageVO<OrderDTO> page = new PageVO<>();
        page.setPages((long) Math.ceil((double) orderDTOList.size() / pageSize));
        page.setTotal((long) orderDTOList.size());
        boolean ret=page.setCuttingList(pageNo, pageSize, orderDTOList);
        if(!ret){
            return ResponseResult.errorResult(400,"分页查询错误");
        }

//        List<OrderDTO> orderList=this.baseMapper.get(ordersQuery);
//
//        PageVO<OrderDTO> page = new PageVO<>();
//        page.setPages((long) Math.ceil((double) orderList.size() / pageSize));
//        page.setTotal((long) orderList.size());
//        boolean ret=page.setCuttingList(pageNo, pageSize, orderList);
//        if(!ret){
//            return ResponseResult.errorResult(400,"分页查询错误");
//        }

        return ResponseResult.okResult(page);
    }


}
