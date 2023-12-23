package com.example.cloudcomputing2023.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cloudcomputing2023.pojo.Payment;
import com.example.cloudcomputing2023.pojo.Product;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}