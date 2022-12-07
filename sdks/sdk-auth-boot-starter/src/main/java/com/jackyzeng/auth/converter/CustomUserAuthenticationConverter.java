package com.jackyzeng.auth.converter;

import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

/**
 * JwtTokenConverter使用DefaultUserAuthenticationConverter从token中获取用户信息
 */
public class CustomUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

}
