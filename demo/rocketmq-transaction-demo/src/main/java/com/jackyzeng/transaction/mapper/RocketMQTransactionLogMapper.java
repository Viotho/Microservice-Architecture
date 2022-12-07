package com.jackyzeng.transaction.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jackyzeng.transaction.model.RocketMQTransactionLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RocketMQTransactionLogMapper extends BaseMapper<RocketMQTransactionLog> {
}
