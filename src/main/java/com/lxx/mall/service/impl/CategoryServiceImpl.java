package com.lxx.mall.service.impl;

import com.lxx.mall.exception.LxxMallException;
import com.lxx.mall.exception.LxxMallExceptionEnum;
import com.lxx.mall.model.dao.CategoryMapper;
import com.lxx.mall.model.pojo.Category;
import com.lxx.mall.model.request.AddCategoryReq;
import com.lxx.mall.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 林修贤
 * @date 2023/2/12
 * @description 目录分类实现类
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public void add(AddCategoryReq addCategoryReq){
        Category category = new Category();
        //根据对应字段自动导入，免去一个一个调用setter
        BeanUtils.copyProperties(addCategoryReq, category);
        Category categoryOld = categoryMapper.selectByName(addCategoryReq.getName());
        if (categoryOld != null){
            throw new LxxMallException(LxxMallExceptionEnum.NAME_EXISTS);
        }
        int count = categoryMapper.insertSelective(category);
        if (count == 0){
            throw new LxxMallException(LxxMallExceptionEnum.INSERT_FAILED);
        }
    }

    @Override
    public void update(Category updateCategory){
        if(updateCategory != null){
            Category categoryOld = categoryMapper.selectByName(updateCategory.getName());
            if(categoryOld != null && !categoryOld.getId().equals(updateCategory.getId())){
                throw new LxxMallException(LxxMallExceptionEnum.NAME_EXISTS);
            }
        }
        int count = categoryMapper.updateByPrimaryKeySelective(updateCategory);
        if(count == 0){
            throw new LxxMallException(LxxMallExceptionEnum.UPDATE_FAILED);
        }
    }
}
