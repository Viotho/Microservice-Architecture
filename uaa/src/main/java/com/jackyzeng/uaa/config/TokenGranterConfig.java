package com.jackyzeng.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
public class TokenGranterConfig {

    @Autowired
    private TokenStore tokenStore;
}
