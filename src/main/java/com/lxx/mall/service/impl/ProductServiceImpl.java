package com.lxx.mall.service.impl;

import com.lxx.mall.exception.LxxMallException;
import com.lxx.mall.exception.LxxMallExceptionEnum;
import com.lxx.mall.model.dao.ProductMapper;
import com.lxx.mall.model.pojo.Product;
import com.lxx.mall.model.request.AddProductReq;
import com.lxx.mall.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 林修贤
 * @date 2023/2/14
 * @description 商品服务实现类
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Override
    public void add(AddProductReq addProductReq){
        Product product = new Product();
        BeanUtils.copyProperties(addProductReq, product);
        Product productOld = productMapper.selectByName(addProductReq.getName());
        if(productOld != null){
            throw new LxxMallException(LxxMallExceptionEnum.NAME_EXISTS);
        }
        int count  = productMapper.insertSelective(product);
        if (count == 0){
            throw new LxxMallException(LxxMallExceptionEnum.INSERT_FAILED);
        }
    }
}
