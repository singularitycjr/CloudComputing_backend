package com.example.cloudcomputing2023.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.cloudcomputing2023.mapper.CustomerMapper;
import com.example.cloudcomputing2023.pojo.Customer;
import com.example.cloudcomputing2023.service.ICustomerService;
import com.example.cloudcomputing2023.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public ResponseResult getNameById(Long id) {
        Customer customer = this.getOne(
                Wrappers.<Customer>lambdaQuery().eq(Customer::getCustomerId, id)
        );

        return ResponseResult.okResult(customer.getCustomerName());
    }

    @Override
    public ResponseResult getAll() {
        return ResponseResult.okResult(customerMapper.selectList(null));
    }
}
