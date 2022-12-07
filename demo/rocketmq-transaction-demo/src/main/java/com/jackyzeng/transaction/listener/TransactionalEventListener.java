package com.jackyzeng.transaction.listener;

import com.jackyzeng.transaction.model.SampleObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(topic = "topic", consumerGroup = "cloud-group")
public class TransactionalEventListener implements RocketMQListener<SampleObject> {

    @Override
    public void onMessage(SampleObject sampleObject) {
        log.info("received message: {}",sampleObject);
        log.info("Operation with received message.");
    }
}
