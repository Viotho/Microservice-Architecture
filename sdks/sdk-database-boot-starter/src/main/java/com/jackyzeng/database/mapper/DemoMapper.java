package com.jackyzeng.database.mapper;


import com.jackyzeng.common.model.BaseModel;
import com.jackyzeng.database.model.BaseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DemoMapper {

    List<BaseModel> findList(@Param("condition") BaseDto condition);

    int insertBatch(@Param("list") List<BaseModel> entities);

    int deleteBatch(@Param("ids") List<Long> ids);
}
