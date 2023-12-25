package com.example.cloudcomputing2023.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cloudcomputing2023.pojo.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {


}
