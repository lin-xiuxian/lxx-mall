package com.lxx.mall.controller;

import com.lxx.mall.common.ApiRestResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 林修贤
 * @date 2023/2/17
 * @description 购物车 Controller
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    @PostMapping("/add")
    public ApiRestResponse add(@RequestParam Integer productId, @RequestParam Integer count){
        return null;
    }
}
