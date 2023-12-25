package com.example.cloudcomputing2023.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.cloudcomputing2023.mapper.ProductMapper;
import com.example.cloudcomputing2023.pojo.Customer;
import com.example.cloudcomputing2023.pojo.Product;
import com.example.cloudcomputing2023.query.PageQuery;
import com.example.cloudcomputing2023.query.ProductQuery;
import com.example.cloudcomputing2023.service.IProductService;
import com.example.cloudcomputing2023.vo.PageVO;
import com.example.cloudcomputing2023.vo.ResponseResult;
import org.springframework.stereotype.Service;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    @Override
    public ResponseResult get(ProductQuery productQuery) {
        Integer pageNo = productQuery.getPageNo();
        Integer pageSize = productQuery.getPageSize();

        if(productQuery.getPriceLowerBound()==null){
            productQuery.setPriceLowerBound(new BigDecimal(0));
        }
        if(productQuery.getPriceUpperBound()==null){
            productQuery.setPriceUpperBound(BigDecimal.valueOf(Long.MAX_VALUE));
        }

        List<Product> productList = this.list(
                Wrappers.<Product>lambdaQuery()
                        .like(StrUtil.isNotBlank(productQuery.getProductName()), Product::getProductName, productQuery.getProductName())
                        .eq(ObjectUtil.isAllNotEmpty(productQuery.getProductId()), Product::getProductId, productQuery.getProductId())
                        .between(Product::getUnitPrice, productQuery.getPriceLowerBound(), productQuery.getPriceUpperBound())
        );



        PageVO<Product> page = new PageVO<>();
        page.setPages((long) Math.ceil((double) productList.size() / pageSize));
        page.setTotal((long) productList.size());
        boolean ret=page.setCuttingList(pageNo, pageSize, productList);
        if(!ret){
            return ResponseResult.errorResult(400,"分页查询错误");
        }

        return ResponseResult.okResult(page);
    }

//    @Override
//    public ResponseResult getByName(String name) {
//        List<Product> productList = this.list(
//                Wrappers.<Product>lambdaQuery()
//                .like( Product::getProductName, name)
//        );
//        PageVO<Product> page = new PageVO<>();
//        page.setPages(1L);
//        return ResponseResult.okResult(productList);
//    }
//
//    @Override
//    public ResponseResult getById(Long id) {
//        Product product = this.getOne(
//                Wrappers.<Product>lambdaQuery()
//                        .eq( Product::getProductId, id)
//        );
//        PageVO<Product> page = new PageVO<>();
//        page.setPages(1L);
//        page.setTotal(1L);
//        page.setCuttingList(product);
//        return ResponseResult.okResult(page);
//    }
}
