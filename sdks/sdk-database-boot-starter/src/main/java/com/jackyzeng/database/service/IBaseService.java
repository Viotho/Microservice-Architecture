package com.jackyzeng.database.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jackyzeng.common.model.BaseModel;
import com.jackyzeng.database.model.PageQuery;

public interface IBaseService<T extends BaseModel, Q extends PageQuery> extends IService<T> {
    Page<T> page(Q query);
}
