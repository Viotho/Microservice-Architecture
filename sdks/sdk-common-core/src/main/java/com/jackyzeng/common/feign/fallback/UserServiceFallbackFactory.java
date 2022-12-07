package com.jackyzeng.common.feign.fallback;

import com.jackyzeng.common.feign.UserService;
import com.jackyzeng.common.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

@Slf4j
public class UserServiceFallbackFactory implements FallbackFactory<UserService> {

    @Override
    public UserService create(Throwable throwable) {
        return new UserService() {
            @Override
            public SysUser findUserByUsername(String username) {
                log.error("通过用户名查询用户异常:{}", username, throwable);
                return new SysUser();
            }

            @Override
            public SysUser findUserByMobile(String mobile) {
                log.error("通过用户名查询用户异常:{}", mobile, throwable);
                return new SysUser();
            }
        };
    }
}
