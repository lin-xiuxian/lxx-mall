package com.lxx.mall.controller;

import com.github.pagehelper.PageInfo;
import com.lxx.mall.common.ApiRestResponse;
import com.lxx.mall.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 林修贤
 * @date 2023/2/21
 * @description 后台 订单管理 controller
 */
@RestController
public class OrderAdminController {

    @Autowired
    OrderService orderService;

    @GetMapping("/admin/order/list")
    @ApiOperation("后台订单列表")
    public ApiRestResponse listForAdmin(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        PageInfo pageInfo = orderService.listForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    @PostMapping("/admin/order/delivered")
    @ApiOperation("管理员发货")
    public ApiRestResponse delivered(@RequestParam String orderNo){
        orderService.deliver(orderNo);
        return ApiRestResponse.success();
    }

    @PostMapping("/order/finish")
    @ApiOperation("完结订单")
    public ApiRestResponse finish(@RequestParam String orderNo){
        orderService.finish(orderNo);
        return ApiRestResponse.success();
    }
}
