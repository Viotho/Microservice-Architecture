package com.jackyzeng.transaction.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("rocketmq_transaction_log")
public class RocketMQTransactionLog {

    private Integer id;

    private String transactionId;

    private String log;
}
