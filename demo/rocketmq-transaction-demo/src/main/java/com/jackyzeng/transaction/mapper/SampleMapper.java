package com.jackyzeng.transaction.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jackyzeng.transaction.model.SampleObject;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SampleMapper extends BaseMapper<SampleObject> {

}
