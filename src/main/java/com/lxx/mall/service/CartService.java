package com.lxx.mall.service;

import com.lxx.mall.model.vo.CartVO;

import java.util.List;

/**
 * @author 林修贤
 * @date 2023/2/17
 * @description 购物车service 类
 */
public interface CartService {
    List<CartVO> list(Integer userId);

    List<CartVO> add(Integer userId, Integer productId, Integer count);
}
