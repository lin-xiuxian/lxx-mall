package com.lxx.mall.service;

import com.lxx.mall.model.pojo.Category;
import com.lxx.mall.model.request.AddCategoryReq;

/**
 * @author 林修贤
 * @date 2023/2/12
 * @description 分类目录 Service 接口
 */
public interface CategoryService {

    void add(AddCategoryReq addCategoryReq);

    void update(Category updateCategory);

    void delete(Integer id);
}
