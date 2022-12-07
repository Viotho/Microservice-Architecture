package com.jackyzeng.common.config;

import com.jackyzeng.common.feign.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class DefaultWebMvcConfig {

    @Lazy
    @Autowired
    private UserService userService;

}
