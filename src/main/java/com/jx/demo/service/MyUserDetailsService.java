package com.jx.demo.service;

import com.jx.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("-------------自定义登录-----------");
        // 1.查询用户信息
        MyUserDetails myUserDetails = userService.queryUserByUsername(username);

        if (myUserDetails == null){
            throw new UsernameNotFoundException("该用户不存在!");
        }

        // 2.查询用户对应的角色列表
        String roleCode = userService.queryRoleCodesByUsername(username);


        // 3.查询角色列表对应的权限信息
        List<String> menuCodes = userService.queryMenuCodeByRoleCode(roleCode);
        myUserDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",",menuCodes)));

        return myUserDetails;
    }

}
