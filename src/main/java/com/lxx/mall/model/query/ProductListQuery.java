package com.lxx.mall.model.query;

import java.util.List;

/**
 * @author 林修贤
 * @date 2023/2/15
 * @description  查询商品列表的query
 */
public class ProductListQuery {

    private String keyword;

    private List<Integer> categoryIds;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }
}
