package com.jackyzeng.demo.message.service;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ReceiveService {

    @StreamListener("inputChannel-1")
    public void receiveInputChannel1(String receiveMsg) {
        System.out.println("input receive: " + receiveMsg);
    }

    @StreamListener("inputChannel-2")
    public void receiveInputChannel2(@Payload Object object) {
        System.out.println("input receive: " + object);
    }

//    @StreamListener("inputChannel-2")
//    public void receiveInputChannel2(Message<Object> message) {
//        System.out.println("input receive: " + message);
//    }
}
