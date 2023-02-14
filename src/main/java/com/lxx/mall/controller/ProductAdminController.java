package com.lxx.mall.controller;

import com.lxx.mall.common.ApiRestResponse;
import com.lxx.mall.model.request.AddProductReq;
import com.lxx.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author 林修贤
 * @date 2023/2/14
 * @description 后台商品管理
 */
@Controller
public class ProductAdminController {
    @Autowired
    ProductService productService;
    @PostMapping("/admin/product/add")
    public ApiRestResponse addProduct(@Valid @RequestBody AddProductReq addProductReq) {
        productService.add(addProductReq);
        return ApiRestResponse.success();
    }
}
