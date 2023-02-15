package com.lxx.mall.controller;

import com.github.pagehelper.PageInfo;
import com.lxx.mall.common.ApiRestResponse;
import com.lxx.mall.model.pojo.Product;
import com.lxx.mall.model.request.ProductListReq;
import com.lxx.mall.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 林修贤
 * @date 2023/2/15
 * @description 前台商品controller
 */
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @ApiOperation("商品详情")
    @PostMapping("/product/detail")
    public ApiRestResponse detail(@RequestParam Integer id){
        Product product = productService.detail(id);
        return ApiRestResponse.success(product);
    }

    @ApiOperation("商品列表")
    @PostMapping("/product/list")
    public ApiRestResponse list(ProductListReq productListReq){
        PageInfo list = productService.list(productListReq);
        return ApiRestResponse.success(list);
    }

}
