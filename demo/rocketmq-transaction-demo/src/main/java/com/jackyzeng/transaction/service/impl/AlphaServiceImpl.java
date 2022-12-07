package com.jackyzeng.transaction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jackyzeng.transaction.mapper.RocketMQTransactionLogMapper;
import com.jackyzeng.transaction.mapper.SampleMapper;
import com.jackyzeng.transaction.model.RocketMQTransactionLog;
import com.jackyzeng.transaction.model.SampleObject;
import com.jackyzeng.transaction.service.AlphaService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;


@Service
public class AlphaServiceImpl extends ServiceImpl<SampleMapper, SampleObject> implements AlphaService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private RocketMQTransactionLogMapper rocketMQTransactionLogMapper;

    @Override
    public void doWithTransaction(Integer id) {
        if (Objects.nonNull(id)) {
            String transactionId = UUID.randomUUID().toString();
            // Send message to target service listener
            rocketMQTemplate.sendMessageInTransaction("cloud-group", "topic",
                    MessageBuilder.withPayload(
                            SampleObject.builder()
                                    .id(id)
                                    .status("status")
                                    .build()
                            )
                            .setHeader(RocketMQHeaders.TRANSACTION_ID, transactionId)
                            .setHeader("id", id)
                            .build(),
                    null);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeStatusWithRocketMqLog(Integer id, String status, String transactionId) {
        SampleObject sampleObject = getById(id);
        sampleObject.setStatus(status);
        saveOrUpdate(sampleObject);

        rocketMQTransactionLogMapper.insert(RocketMQTransactionLog.builder()
                .transactionId(transactionId)
                .log("RocketMQ Transaction Log")
                .build());
    }
}
