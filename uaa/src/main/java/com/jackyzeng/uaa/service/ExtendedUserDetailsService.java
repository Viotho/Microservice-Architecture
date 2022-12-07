package com.jackyzeng.uaa.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ExtendedUserDetailsService extends UserDetailsService {
    UserDetails loadUserByMobile(String mobileNum);
}
