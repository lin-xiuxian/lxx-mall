package com.lxx.mall.service;

import com.lxx.mall.model.request.CreateOrderReq;

/**
 * @author 林修贤
 * @date 2023/2/18
 * @description 订单接口
 */
public interface OrderService {
    String create(CreateOrderReq createOrderReq);
}