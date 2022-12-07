package com.jackyzeng.demo.message.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

@EnableBinding({RocketMqConfig.RocketMqSource.class, RocketMqConfig.RocketMqSink.class})
public class RocketMqConfig {
    public interface RocketMqSource {
        @Output("outputChannel-1")
        MessageChannel outputChannel1();
    }

    public interface RocketMqSink {
        @Input("inputChannel-1")
        SubscribableChannel inputChannel1();

        @Input("inputChannel-2")
        SubscribableChannel inputChannel2();
    }
}
