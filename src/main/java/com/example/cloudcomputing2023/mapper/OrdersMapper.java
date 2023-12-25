package com.example.cloudcomputing2023.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cloudcomputing2023.dto.OrderDTO;
import com.example.cloudcomputing2023.pojo.Customer;
import com.example.cloudcomputing2023.pojo.OrderDetail;
import com.example.cloudcomputing2023.pojo.Orders;
import com.example.cloudcomputing2023.query.OrdersQuery;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

//    @Select("SELECT     *   " +
//            "FROM    orders     LEFT JOIN customer ON orders.customerId = customer.customerId")
//    @Select("SELECT     orderId,customerId,orderDate   " +
//            "FROM    orders ")
    @Select("<script>" +
            "SELECT orders.orderId as orderId, orders.customerId as customerId,customer.customerName as customerName, orderdate,paymentDate,paymentMethod " +
            "FROM orders " +
            "LEFT JOIN customer ON orders.customerId = customer.customerId " +
            "LEFT JOIN payment ON payment.orderId = orders.orderId " +
            "WHERE orders.orderDate BETWEEN #{startTime} AND #{endTime}" +
            "<if test='customerName != null'>" +
            "   AND customer.customerName LIKE CONCAT('%', #{customerName}, '%')" +
            "</if>" +
            "<if test='orderId != null'>" +
            "   AND orders.orderId = #{orderId}" +
            "</if>" +
            "<if test='customerId != null'>" +
            "   AND orders.customerId = #{customerId}" +
            "</if>" +
            "</script>")
    public List<OrderDTO> get(OrdersQuery ordersQuery);
}

