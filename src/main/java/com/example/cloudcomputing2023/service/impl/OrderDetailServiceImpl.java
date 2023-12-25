package com.example.cloudcomputing2023.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.cloudcomputing2023.dto.OrderDTO;
import com.example.cloudcomputing2023.dto.OrderDetailDTO;
import com.example.cloudcomputing2023.dto.StatisticsDTO;
import com.example.cloudcomputing2023.mapper.OrderDetailMapper;
import com.example.cloudcomputing2023.mapper.ProductMapper;
import com.example.cloudcomputing2023.pojo.Customer;
import com.example.cloudcomputing2023.pojo.OrderDetail;
import com.example.cloudcomputing2023.pojo.Orders;
import com.example.cloudcomputing2023.pojo.Product;
import com.example.cloudcomputing2023.query.StatisticsQuery;
import com.example.cloudcomputing2023.service.IOrderDetailService;
import com.example.cloudcomputing2023.vo.PageVO;
import com.example.cloudcomputing2023.vo.ResponseResult;
import com.github.yulichang.toolkit.JoinWrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private OrdersServiceImpl ordersService;

    @Override
    public ResponseResult getById(Integer id) {
        List<OrderDetail> orderDetailList = this.list(
                Wrappers.<OrderDetail>lambdaQuery()
                        .eq(ObjectUtil.isAllNotEmpty(id), OrderDetail::getOrderId, id)
        );

        if (orderDetailList == null) {
            return ResponseResult.errorResult(400, "订单详情不存在");
        }

        List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();

        for (OrderDetail orderDetail : orderDetailList) {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            BeanUtils.copyProperties(orderDetail, orderDetailDTO);

            orderDetailDTO.setProductName(productService.getBaseMapper().selectById(orderDetail.getProductId()).getProductName());
            orderDetailDTO.setSumPrice(orderDetailDTO.getUnitPrice().multiply(new BigDecimal(orderDetailDTO.getQuantity())));
            orderDetailDTOList.add(orderDetailDTO);
        }

        return ResponseResult.okResult(orderDetailDTOList);
    }

    public ResponseResult getStatistics(StatisticsQuery statisticsQuery) {
        Integer pageNo = statisticsQuery.getPageNo();
        Integer pageSize = statisticsQuery.getPageSize();


        if (statisticsQuery.getStartTime() == null) {
            statisticsQuery.setStartTime(java.sql.Date.valueOf("2000-01-01"));
        }
        if (statisticsQuery.getEndTime() == null) {
            statisticsQuery.setEndTime(java.sql.Date.valueOf("2023-12-25"));
        }

//        if(statisticsQuery.getStartTime()==null){
//            statisticsQuery.setStartTime(java.sql.Date.valueOf(LocalDate.MAX));
//        }
//        if(statisticsQuery.getEndTime()==null){
//            statisticsQuery.setEndTime(java.sql.Date.valueOf(LocalDate.MIN));
//        }
//
////        MPJLambdaWrapper<OrderDetail> wrapper = JoinWrappers.lambda(OrderDetail.class)
////                .select("orderdetailid","o.orderid","p.productid","quantity","t.unitprice")
//////                .selectAll(OrderDetail.class)
////                .leftJoin(Orders.class,"o", Orders::getOrderId,OrderDetail::getOrderId)
////                .leftJoin(Product.class,"p", Product::getProductId, OrderDetail::getProductId)
////                .eq(ObjectUtil.isAllNotEmpty(statisticsQuery.getProductId()), OrderDetail::getProductId, statisticsQuery.getProductId())
////                .like(StrUtil.isNotBlank(statisticsQuery.getProductName()), Product::getProductName, statisticsQuery.getProductName())
////                .between(Orders::getOrderDate, statisticsQuery.getStartTime(), statisticsQuery.getEndTime());
//
//
        List<OrderDetail> orderDetailList = this.list(
                Wrappers.<OrderDetail>lambdaQuery()
                        .eq(ObjectUtil.isAllNotEmpty(statisticsQuery.getProductId()), OrderDetail::getProductId, statisticsQuery.getProductId())
        );

        for(int i=0;i<orderDetailList.size();i++){
            OrderDetail orderDetail = orderDetailList.get(i);

            if(StrUtil.isNotBlank(statisticsQuery.getProductName())){
                Product product=productService.getOne(
                        Wrappers.<Product>lambdaQuery().eq(Product::getProductId, orderDetail.getProductId())
                );
                if(!product.getProductName().contains(statisticsQuery.getProductName())){
                    orderDetailList.remove(orderDetail);
                    i--;
                    continue;
                }
            }

            Orders orders=ordersService.getOne(
                    Wrappers.<Orders>lambdaQuery().eq(Orders::getOrderId, orderDetail.getOrderId())
            );
            if(orders.getOrderDate().compareTo(statisticsQuery.getStartTime())<0 || orders.getOrderDate().compareTo(statisticsQuery.getEndTime())>0){
                orderDetailList.remove(orderDetail);
                i--;
                continue;
            }
        }

        System.out.println(orderDetailList);
        System.out.println("111");
        // 分组并计算总量和总销售额
//        Map<Integer, StatisticsDTO> result = orderDetailList.stream().filter(Objects::nonNull)
//                .collect(Collectors.groupingBy(OrderDetail::getProductId,
//                        Collectors.reducing(
//                                new StatisticsDTO(),
//                                detail -> {
//                                    StatisticsDTO statisticsDTO = new StatisticsDTO();
//                                    statisticsDTO.setProductId(detail.getProductId());
//                                    System.out.println("detail.getProductId");
//                                    System.out.println(detail.getProductId());
//                                    statisticsDTO.setSaleQuantity(detail.getQuantity());
//                                    statisticsDTO.setSaleAmount(
//                                            detail.getUnitPrice().multiply(BigDecimal.valueOf(detail.getQuantity())));
//                                    return statisticsDTO;
//                                },
//                                (dto1, dto2) -> {
//                                    System.out.println("dto2.getProductId()");
//                                    System.out.println(dto2.getProductId());
//                                    dto1.setProductId(dto2.getProductId());
//                                    dto1.setProductName(dto2.getProductName());
//                                    dto1.setSaleQuantity(dto1.getSaleQuantity()+dto2.getSaleQuantity());
//                                    dto1.setSaleAmount(dto1.getSaleAmount().add(dto2.getSaleAmount()));
//                                    return dto1;
//                                }
//                        )
//                ));

        Map<Integer, StatisticsDTO> result = orderDetailList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        OrderDetail::getProductId,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                details -> details.stream()
                                        .filter(Objects::nonNull)
                                        .map(detail -> {
                                            StatisticsDTO statisticsDTO = new StatisticsDTO();
                                            statisticsDTO.setProductId(detail.getProductId());
                                            statisticsDTO.setSaleQuantity(detail.getQuantity());
                                            statisticsDTO.setSaleAmount(detail.getUnitPrice()
                                                    .multiply(BigDecimal.valueOf(detail.getQuantity())));
                                            return statisticsDTO;
                                        })
                                        .reduce((dto1, dto2) -> {
                                            dto1.setSaleQuantity(dto1.getSaleQuantity() + dto2.getSaleQuantity());
                                            dto1.setSaleAmount(dto1.getSaleAmount().add(dto2.getSaleAmount()));
                                            return dto1;
                                        })
                                        .orElse(new StatisticsDTO()) // 如果没有统计信息，返回空的 StatisticsDTO
                        )
                ));


        List<StatisticsDTO> statisticsDTOList = new ArrayList<>(result.values());

        for (StatisticsDTO statisticsDTO : statisticsDTOList) {
            Product product = productService.getOne(
                    Wrappers.<Product>lambdaQuery().eq(Product::getProductId, statisticsDTO.getProductId())
            );
            statisticsDTO.setProductName(product.getProductName());
        }


        PageVO<StatisticsDTO> page = new PageVO<>();
        page.setPages((long) Math.ceil((double) statisticsDTOList.size() / pageSize));
        page.setTotal((long) statisticsDTOList.size());
        boolean ret = page.setCuttingList(pageNo, pageSize, statisticsDTOList);
        if (!ret) {
            return ResponseResult.errorResult(400, "分页查询错误");
        }

        return ResponseResult.okResult(page);

    }
}
