package com.jackyzeng.database.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jackyzeng.common.model.BaseModel;
import com.jackyzeng.database.model.PageQuery;
import com.jackyzeng.database.service.IBaseService;

public abstract class BaseServiceImpl<T extends BaseModel, Q extends PageQuery, M extends BaseMapper<T>> extends ServiceImpl<M, T> implements IBaseService<T, Q> {

    @Override
    public Page<T> page(Q query) {
        return null;
    }
}
