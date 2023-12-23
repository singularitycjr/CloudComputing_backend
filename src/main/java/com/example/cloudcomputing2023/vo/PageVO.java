package com.example.cloudcomputing2023.vo;


import com.example.cloudcomputing2023.pojo.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageVO<T> {
    private Long total;
    private Long pages;
    private List<T> list;

    public boolean setCuttingList(int pageNo , int pageSize , List<T> productList) {
        int start = (pageNo - 1) * pageSize; // 计算起始位置
        int end = pageNo * pageSize; // 计算结束位置
        list = new ArrayList<>();
        if (start < productList.size()) {
            if (end > productList.size()) {
                list = productList.subList(start, productList.size());
            } else {
                list = productList.subList(start, end);
            }
            return true;
        } else {
            return false;
        }
    }



    public boolean setCuttingList( T product) {
        if(product == null) {
            return false;
        }
        total=1L;
        pages=1L;
        list = new ArrayList<>();
        list.add(product);
        return true;
    }
}