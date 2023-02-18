package com.lxx.mall.controller;

import com.lxx.mall.common.ApiRestResponse;
import com.lxx.mall.model.request.CreateOrderReq;
import com.lxx.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 林修贤
 * @date 2023/2/18
 * @description 订单 controller
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ApiRestResponse create(@RequestBody CreateOrderReq createOrderReq){
        return null;
    }
}
