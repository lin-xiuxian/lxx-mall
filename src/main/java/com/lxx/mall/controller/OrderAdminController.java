package com.lxx.mall.controller;

import com.github.pagehelper.PageInfo;
import com.lxx.mall.common.ApiRestResponse;
import com.lxx.mall.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 林修贤
 * @date 2023/2/21
 * @description 后台 订单管理 controller
 */
@RestController
@RequestMapping("/admin/order")
public class OrderAdminController {

    @Autowired
    OrderService orderService;

    @GetMapping("/list")
    @ApiOperation("后台订单列表")
    public ApiRestResponse listForAdmin(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        return null;
    }
}
