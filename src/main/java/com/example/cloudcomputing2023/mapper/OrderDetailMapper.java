package com.example.cloudcomputing2023.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cloudcomputing2023.dto.StatisticsDTO;
import com.example.cloudcomputing2023.pojo.Customer;
import com.example.cloudcomputing2023.pojo.OrderDetail;
import com.example.cloudcomputing2023.pojo.Orders;
import com.example.cloudcomputing2023.query.StatisticsQuery;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail>{

    @Select("<script>" +
            "SELECT " +
            "    orderdetail.productId as productId, " +
            "    product.productName as productName, " +
            "    SUM(orderdetail.quantity) as saleQuantity, " +
            "    SUM(orderdetail.quantity * orderdetail.unitPrice) as saleAmount " +
            "FROM orderdetail " +
            "LEFT JOIN orders ON orders.orderid = orderdetail.orderid " +
            "LEFT JOIN product ON product.productid = orderdetail.productid " +
            "WHERE orders.orderDate BETWEEN #{startTime} AND #{endTime} " +
            "<if test='productName != null'>" +
            "    AND product.productName LIKE CONCAT('%', #{productName}, '%')" +
            "</if>" +
            "<if test='productId != null'>" +
            "    AND orderdetail.productId = #{productId}" +
            "</if>" +
            "GROUP BY orderdetail.productId, product.productName " +
            "</script>")
    public List<StatisticsDTO> getStatistics(StatisticsQuery statisticsQuery );
}
