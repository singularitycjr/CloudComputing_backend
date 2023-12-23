package com.example.cloudcomputing2023.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cloudcomputing2023.pojo.Customer;
import com.example.cloudcomputing2023.pojo.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
}
