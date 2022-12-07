package com.jackyzeng.logging.config;

import com.jackyzeng.logging.aspect.SysLogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class SysLogAutoConfigure {

    @Bean
    public SysLogAspect sysLogAspect() {
        return new SysLogAspect();
    }
}
