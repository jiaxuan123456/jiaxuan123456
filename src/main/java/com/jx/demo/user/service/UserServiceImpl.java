package com.jx.demo.user.service;


import com.jx.demo.service.MyUserDetails;
import com.jx.demo.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;


    @Override
    public MyUserDetails queryUserByUsername(String username) {

        return userMapper.queryUserByUsername(username);
    }

    @Override
    public String queryRoleCodesByUsername(String username) {
        return userMapper.queryRoleCodesByUsername(username);
    }

    @Override
    public List<String> queryMenuCodeByRoleCode(String roleCode) {
        return userMapper.queryMenuCodeByRoleCode(roleCode);
    }
}
