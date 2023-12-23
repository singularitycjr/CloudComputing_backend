package com.example.cloudcomputing2023.controller;

import com.example.cloudcomputing2023.service.ICustomerService;
import com.example.cloudcomputing2023.vo.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customer")
@Tag(name = "顾客数据")
@Slf4j
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/getNameById/{id}")
    @Operation(summary = "获取用户姓名信息")
    public ResponseResult getNameById(@PathVariable("id") Long id){

        return this.customerService.getNameById(id);
    }

    @GetMapping("/getAll")
    @Operation(summary = "获取所有用户信息")
    public ResponseResult getAll() {

        return this.customerService.getAll();
    }






}
