package com.lxx.mall.service.impl;

import com.lxx.mall.common.Constant;
import com.lxx.mall.exception.LxxMallException;
import com.lxx.mall.exception.LxxMallExceptionEnum;
import com.lxx.mall.filter.UserFilter;
import com.lxx.mall.model.request.CreateOrderReq;
import com.lxx.mall.model.vo.CartVO;
import com.lxx.mall.service.CartService;
import com.lxx.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 林修贤
 * @date 2023/2/18
 * @description 订单接口实现类
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CartService cartService;

    public String create(CreateOrderReq createOrderReq){
        //拿到用户ID
        Integer userId = UserFilter.currentUser.getId();
        //从购物车查找已勾选的商品
        List<CartVO> cartVOList = cartService.list(userId);
        ArrayList<CartVO> cartVOListTemp = new ArrayList<>();
        for (int i = 0; i < cartVOList.size(); i++) {
            CartVO cartVO = cartVOList.get(i);
            if (cartVO.getSelected().equals(Constant.Cart.CHECKED)){
                cartVOListTemp.add(cartVO);
            }
        }
        cartVOList = cartVOListTemp;
        //如果已勾选的为空， 报错
        if(CollectionUtils.isEmpty(cartVOList)){
            throw new LxxMallException(LxxMallExceptionEnum.CART_EMPTY);
        }
        //判断商品是否存在，上下架状态，库存
        //把购物车对象转化为订单item对象

        //扣库存

        //把购物车中已勾选的商品删除

        //生成订单

        //生成订单号， 独立规则

        //循环保存每个商品到 order_item 表
        //把结果返回
        return null;
    }
}
