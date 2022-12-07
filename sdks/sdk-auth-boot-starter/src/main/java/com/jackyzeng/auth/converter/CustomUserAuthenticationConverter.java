package com.jackyzeng.auth.converter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.Collection;
import java.util.Map;

/**
 * JwtTokenConverter使用DefaultUserAuthenticationConverter从token中获取用户信息
 */
public class CustomUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

}
