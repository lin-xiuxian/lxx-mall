package com.lxx.mall.model.query;

import java.util.Date;

/**
 * @author 林修贤
 * @date 2023/2/26
 * @description 订单量统计 query
  */
public class OrderStatisticsQuery {
    private Date startDate;
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
