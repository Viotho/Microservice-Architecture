package com.jackyzeng.auth.converter;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;

/**
 * 认证服务器在JWT中添加自定义数据
 */
public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        LoginUser loginUser = (LoginUser) authentication.getUserAuthentication().getPrincipal();
        HashMap<Object, Object> additionalInformation = new HashMap<>();
        additionalInformation.put("id", loginUser.getId());
        additionalInformation.put("mobile", loginUser.getMobile());
        return super.enhance(accessToken, authentication);
    }
}
