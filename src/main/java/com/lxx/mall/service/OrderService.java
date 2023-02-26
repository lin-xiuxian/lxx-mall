package com.lxx.mall.service;

import com.github.pagehelper.PageInfo;
import com.lxx.mall.model.request.CreateOrderReq;
import com.lxx.mall.model.vo.OrderStatisticsVO;
import com.lxx.mall.model.vo.OrderVO;

import java.util.Date;
import java.util.List;

/**
 * @author 林修贤
 * @date 2023/2/18
 * @description 订单接口
 */
public interface OrderService {
    String create(CreateOrderReq createOrderReq);

    OrderVO detail(String orderNo);

    PageInfo listForCustomer(Integer pageNum, Integer pageSize);

    void cancel(String orderNo);

    String qrcode(String orderNo);

    void pay(String orderNo);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    void deliver(String orderNo);

    void finish(String orderNo);

    List<OrderStatisticsVO> statistics(Date startDate, Date endDate);
}
