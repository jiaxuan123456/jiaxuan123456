package com.jx.demo.user.service;

import com.jx.demo.service.MyUserDetails;

import java.util.List;


public interface UserService {


    MyUserDetails queryUserByUsername(String username);

    String queryRoleCodesByUsername(String username);

    List<String> queryMenuCodeByRoleCode(String roleCode);


}
