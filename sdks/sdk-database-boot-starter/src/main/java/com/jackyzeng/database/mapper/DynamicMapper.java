package com.jackyzeng.database.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DynamicMapper {

    int dynamicInsertSql(@Param("sql") String sql);

    int deleteSql(@Param("sql") String sql);

    int dynamicBatchInsertSql(List<String> value);

    List<Map<String, Object>> selectMapSql(@Param("sql") String sql);

    int selectCountBySql(@Param("sql") String sql);

    int dynamicUpdateSql(@Param("sql") String sql);

}
