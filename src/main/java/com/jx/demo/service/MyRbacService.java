package com.jx.demo.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component("rbacService")
public class MyRbacService {

    /**
     * 判断该用户是否具备相应操作权限
     *
     * @param request
     * @param authentication
     * @return
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication){
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails) principal;
            String requestURI = request.getRequestURI();

            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(requestURI);
            return userDetails.getAuthorities().contains(simpleGrantedAuthority);
        }

        return false;

    }

}
