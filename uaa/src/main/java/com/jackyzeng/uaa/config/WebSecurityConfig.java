package com.jackyzeng.uaa.config;

import com.jackyzeng.uaa.auth.CustomAuthenticationFailureHandler;
import com.jackyzeng.uaa.auth.CustomFilterSecurityInterceptor;
import com.jackyzeng.uaa.auth.CustomLogoutSuccessHandler;
import com.jackyzeng.uaa.service.ExtendedUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ExtendedUserDetailsService userDetailsService;

    // 配置认证管理器AuthenticationManager。所有 UserDetails 相关的，包含 PasswordEncoder 密码等。
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
        super.configure(auth);
    }

    // 配置 WebSecurity 。而 WebSecurity 是基于 Servlet Filter 用来配置 springSecurityFilterChain 。
    // 使用较多的使其ignoring() 方法用来忽略 Spring Security 对静态资源的控制。
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/error",
                "/static/**",
                "/v2/api-docs/**",
                "/swagger-resources/**",
                "/webjars/**",
                "/favicon.ico"
        );
        super.configure(web);
    }

    // 配置 HttpSecurity 。 HttpSecurity 用于构建一个安全过滤器链 SecurityFilterChain 。SecurityFilterChain 最终被注入核心过滤器 。
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/fonts/**", "/index").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .failureUrl("/login-error").failureHandler(customAuthenticationFailureHandler()).permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout-success")
                .logoutSuccessHandler(logoutSuccessHandler())
//                .addLogoutHandler(logoutHandler)
                .clearAuthentication(true)
                .invalidateHttpSession(true)
//                .deleteCookies("cookieNamesToClear")
                .and()
                .rememberMe()
                .userDetailsService(userDetailsService)
                .tokenValiditySeconds(14 * 24 * 60 * 60)
                .and()
                .addFilterAfter(customFilterSecurityInterceptor(), FilterSecurityInterceptor.class)
        ;
        super.configure(http);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Bean
    public FilterSecurityInterceptor customFilterSecurityInterceptor() {
        CustomFilterSecurityInterceptor filterSecurityInterceptor = new CustomFilterSecurityInterceptor();
        return filterSecurityInterceptor;
    }

}
