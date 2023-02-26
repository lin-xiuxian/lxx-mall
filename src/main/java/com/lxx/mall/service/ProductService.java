package com.lxx.mall.service;

import com.github.pagehelper.PageInfo;
import com.lxx.mall.model.pojo.Product;
import com.lxx.mall.model.request.AddProductReq;
import com.lxx.mall.model.request.ProductListReq;

import java.io.File;
import java.io.IOException;

/**
 * @author 林修贤
 * @date 2023/2/14
 * @description 商品service
 */
public interface ProductService {
    void add(AddProductReq addProductReq);

    void update(Product updateProduct);

    void delete(Integer id);

    void batchUpdateSellStatus(Integer[] ids, Integer sellStatus);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    Product detail(Integer id);

    PageInfo list(ProductListReq productListReq);

    void addProductByExcel(File destFile) throws IOException;
}
