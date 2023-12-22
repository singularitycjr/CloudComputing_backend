package com.example.cloudcomputing2023.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.cloudcomputing2023.pojo.Customer;
import com.example.cloudcomputing2023.vo.ResponseResult;

public interface ICustomerService extends IService<Customer> {
    ResponseResult getNameById(Long id);
    ResponseResult getAll();
}
