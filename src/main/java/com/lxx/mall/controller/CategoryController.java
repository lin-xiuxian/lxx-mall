package com.lxx.mall.controller;

import com.lxx.mall.common.ApiRestResponse;
import com.lxx.mall.common.Constant;
import com.lxx.mall.exception.LxxMallExceptionEnum;
import com.lxx.mall.model.pojo.User;
import com.lxx.mall.model.request.AddCategoryReq;
import com.lxx.mall.service.CategoryService;
import com.lxx.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author 林修贤
 * @date 2023/2/12
 * @description 目录Controller
 */
@Controller
public class CategoryController {
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @PostMapping("/admin/category/add")
    @ResponseBody
    public ApiRestResponse addCategory(HttpSession session, @RequestBody AddCategoryReq addCategoryReq){
        if (addCategoryReq.getName() == null || addCategoryReq.getOrderNum() == null || addCategoryReq.getParentId() == null || addCategoryReq.getType() == null){
            return ApiRestResponse.error(LxxMallExceptionEnum.PARA_NOT_NULL);
        }
        User currentUser = (User)session.getAttribute(Constant.LXX_MALL_USER);
        if (currentUser == null){
            return ApiRestResponse.error(LxxMallExceptionEnum.NEED_LOGIN);
        }
        //校验是否是管理员
        boolean adminRole = userService.checkAdminRole(currentUser);
        if (adminRole){
            //是管理员执行操作
            categoryService.add(addCategoryReq);
            return ApiRestResponse.success();
        } else {
            return ApiRestResponse.error(LxxMallExceptionEnum.NEED_ADMIN);
        }
    }
}
