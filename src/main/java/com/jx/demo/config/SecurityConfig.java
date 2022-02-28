package com.jx.demo.config;

import com.jx.demo.handler.MyDefineHandler;
import com.jx.demo.handler.MyFailHandler;
import com.jx.demo.handler.MySuccessHandler;
import com.jx.demo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private MySuccessHandler mySuccessHandler;

    @Autowired
    private MyFailHandler myFailHandler;

    @Autowired
    private MyDefineHandler myDefineHandler;

    @Resource
    private MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表单提交
        http.formLogin()
                // 自定义登录页
                .loginPage("/login.html")
                // 自定义登录路径
                .loginProcessingUrl("/login")
                // 登录成功跳转页面
                .successHandler(mySuccessHandler)
                .failureHandler(myFailHandler);

        // 授权
        http.authorizeRequests()
                // 不登录也能访问的资源
                .antMatchers("/login.html")
                .permitAll()
                .antMatchers("/error.html").permitAll()
                .antMatchers("/images/*").permitAll()
                // 基于权限进行控制
//                .antMatchers("/user/queryUser").hasAnyAuthority("/user/queryUser")
//                .antMatchers("/sys/log").hasAuthority("/sys/log")
//                .antMatchers("/business").hasAuthority("/business")
                // 动态配置鉴权规则
                .anyRequest().access("@rbacService.hasPermission(request,authentication)");
                // 基于角色进行控制
//                .antMatchers("/user/queryUser").hasAnyRole("/user/queryUser")
                // 其他任何请求都需要认证才能访问
//                .anyRequest().authenticated();

        // 记住我
        http.rememberMe();

        // 异常页面跳转
        http.exceptionHandling()
                .accessDeniedHandler(myDefineHandler);

        // 退出
        http.logout()
                .logoutSuccessUrl("/login.html");

        // 关闭CSRF防护
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
