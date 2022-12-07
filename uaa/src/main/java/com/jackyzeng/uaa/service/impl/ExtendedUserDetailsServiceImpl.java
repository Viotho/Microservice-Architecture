package com.jackyzeng.uaa.service.impl;

import com.jackyzeng.common.feign.UserService;
import com.jackyzeng.common.model.SysUser;
import com.jackyzeng.uaa.model.LoginUser;
import com.jackyzeng.uaa.service.ExtendedUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ExtendedUserDetailsServiceImpl implements ExtendedUserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByMobile(String mobile) {
        SysUser sysUser = userService.findUserByMobile(mobile);
        if (Objects.nonNull(sysUser)) {
            return new LoginUser(sysUser.getId(), sysUser.getMobile(), sysUser.getUsername(), sysUser.getPassword(),
                    AuthorityUtils.commaSeparatedStringToAuthorityList(sysUser.getRoles()));
        } else {
            throw new InternalAuthenticationServiceException("手机号错误");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userService.findUserByUsername(username);
        if (Objects.nonNull(sysUser)) {
            return new LoginUser(sysUser.getId(), sysUser.getMobile(), sysUser.getUsername(), sysUser.getPassword(),
                    AuthorityUtils.commaSeparatedStringToAuthorityList(sysUser.getRoles()));
        } else {
            throw new InternalAuthenticationServiceException("用户名或密码错误");
        }
    }
}
