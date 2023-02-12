package com.lxx.mall.model.request;

/**
 * @author 林修贤
 * @date 2023/2/12
 * @description AddCategoryReq
 */
public class AddCategoryReq {
    private String name;
    private Integer Type;
    private Integer parentId;
    private Integer orderNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
