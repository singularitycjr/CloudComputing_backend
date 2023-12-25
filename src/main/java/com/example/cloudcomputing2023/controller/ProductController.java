package com.example.cloudcomputing2023.controller;


import com.example.cloudcomputing2023.query.PageQuery;
import com.example.cloudcomputing2023.query.ProductQuery;
import com.example.cloudcomputing2023.service.ICustomerService;
import com.example.cloudcomputing2023.service.IProductService;
import com.example.cloudcomputing2023.vo.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@Slf4j
@Tag(name = "商品数据")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/get")
    @Operation(summary = "获取商品信息")
    public ResponseResult get(ProductQuery productQuery ) {
        return this.productService.get(productQuery);
    }

//    @GetMapping("/getByName/{name}")
//    @Operation(summary = "根据商品名称获取商品信息")
//    public ResponseResult getByName(@PathVariable("name") String name){
//
//        return this.productService.getByName(name);
//    }
//
//    @GetMapping("/getById/{id}")
//    @Operation(summary = "获取用户姓名信息")
//    public ResponseResult getById(@PathVariable("id") Long id){
//
//        return this.productService.getById(id);
//    }
}
