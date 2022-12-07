package com.jackyzeng.transaction.listener;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jackyzeng.transaction.mapper.RocketMQTransactionLogMapper;
import com.jackyzeng.transaction.model.RocketMQTransactionLog;
import com.jackyzeng.transaction.service.AlphaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.Objects;

@Slf4j
@RocketMQTransactionListener
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RocketMQTransactionalListener implements RocketMQLocalTransactionListener {

    private final AlphaService alphaService;

    private final RocketMQTransactionLogMapper rocketMQTransactionLogMapper;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object arg) {
        log.info("Executing Local Transaction.");
        MessageHeaders headers = message.getHeaders();
        String transactionId = headers.get(RocketMQHeaders.TRANSACTION_ID, String.class);
        Integer id = headers.get("id", Integer.class);
        log.info("Transaction_Id: {}, Object_Id:{}", transactionId, id);

        try {
            // Execute local transaction
            alphaService.changeStatusWithRocketMqLog(id, "status", transactionId);
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        MessageHeaders headers = message.getHeaders();
        String transactionId = headers.get(RocketMQHeaders.TRANSACTION_ID, String.class);
        log.info("Checking Local Transaction, transaction_id: {}", transactionId);

        LambdaQueryWrapper<RocketMQTransactionLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RocketMQTransactionLog::getTransactionId, transactionId);
        RocketMQTransactionLog transactionLog = rocketMQTransactionLogMapper.selectOne(queryWrapper);
        if (Objects.nonNull(transactionLog)) {
            return RocketMQLocalTransactionState.COMMIT;
        }
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}
