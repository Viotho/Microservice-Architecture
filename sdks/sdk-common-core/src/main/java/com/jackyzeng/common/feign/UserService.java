package com.jackyzeng.common.feign;

import com.jackyzeng.common.feign.fallback.UserServiceFallbackFactory;
import com.jackyzeng.common.model.SysUser;
import com.jackyzeng.common.constants.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = ServiceNameConstants.USER_SERVICE, fallbackFactory = UserServiceFallbackFactory.class, decode404 = true)
public interface UserService {

    @GetMapping("/find-user-by-username")
    SysUser findUserByUsername(@RequestParam("username") String username);

    @GetMapping("/find-user-by-mobile")
    SysUser findUserByMobile(@RequestParam("mobile") String mobile);
}
