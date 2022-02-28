package com.jx.demo.user.mapper;

import com.jx.demo.service.MyUserDetails;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名称
     * @return
     */
    @Select("select username,password,enabled from sys_user where username = #{username}")
    MyUserDetails queryUserByUsername(String username);

    /**
     * 根据用户名查询角色信息
     *
     * @param username 用户名称
     * @return
     */
    @Select("select role_code from sys_role r\n" +
            "left join sys_user_role ur on r.id = ur.role_id\n" +
            "left join sys_user u on u.id = ur.user_id\n" +
            "where u.username = #{username}")
    String queryRoleCodesByUsername(String username);

    /**
     * 根据角色查询权限信息
     *
     * @param roleCode 角色编码
     * @return
     */
    @Select("select menu_code from sys_menu m\n" +
            "left join sys_role_menu rm on m.id = rm.menu_id\n" +
            "left join sys_role r on r.id = rm.role_id\n" +
            "where r.role_code = #{roleCode}")
    List<String> queryMenuCodeByRoleCode(String roleCode);

}
