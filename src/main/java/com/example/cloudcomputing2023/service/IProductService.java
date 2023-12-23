package com.example.cloudcomputing2023.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.cloudcomputing2023.pojo.Product;
import com.example.cloudcomputing2023.query.PageQuery;
import com.example.cloudcomputing2023.query.ProductQuery;
import com.example.cloudcomputing2023.vo.ResponseResult;
import org.springframework.web.bind.annotation.PathVariable;

public interface IProductService extends IService<Product> {

    ResponseResult get(ProductQuery productQuery );

//    ResponseResult getByName( String name);
//
//    ResponseResult getById(Long id);
}
