package com.example.cloudcomputing2023.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.cloudcomputing2023.pojo.OrderDetail;
import com.example.cloudcomputing2023.query.StatisticsQuery;
import com.example.cloudcomputing2023.vo.ResponseResult;

public interface IOrderDetailService extends IService<OrderDetail> {

    ResponseResult getById(Integer id );

    ResponseResult getStatistics(StatisticsQuery statisticsQuery );
}
