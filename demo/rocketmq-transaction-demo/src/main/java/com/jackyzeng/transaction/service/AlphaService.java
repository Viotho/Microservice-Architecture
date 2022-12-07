package com.jackyzeng.transaction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jackyzeng.transaction.model.SampleObject;


public interface AlphaService extends IService<SampleObject> {

    void doWithTransaction(Integer id);

    void changeStatusWithRocketMqLog(Integer id, String status, String transactionId);
}
