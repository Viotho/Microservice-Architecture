package com.jackyzeng.database.model;

import lombok.Getter;

import java.io.Serializable;

public class PageQuery implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 8926994487624178473L;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_PAGE_NUM = 1;
    /**
     * 查询条件，分页页码
     */
    @Getter
    private Integer pageNo;

    /**
     * 查询条件，分页大小
     */
    @Getter
    private Integer pageSize;

    public void setPageNo(Integer pageNo) {
        if (pageNo != null) {
            this.pageNo = pageNo <=0 ? DEFAULT_PAGE_NUM : pageNo;
        }
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize != null) {
            this.pageSize = pageSize <=0 ? DEFAULT_PAGE_SIZE : pageSize;
        }
    }
}
