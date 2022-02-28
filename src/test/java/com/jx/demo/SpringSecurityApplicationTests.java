package com.jx.demo;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.Base64Codec;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import sun.misc.BASE64Decoder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringSecurityApplicationTests {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testJwt() {

        // 当前时间
        long timeMillis = System.currentTimeMillis();
        long date = timeMillis + 60 * 1000 * 60 * 24 * 14;

        String token = Jwts.builder()
                .setId("888")
                .setIssuedAt(new Date())
                .setSubject("user_jx")
                .signWith(SignatureAlgorithm.HS256, "jwt_key")
                .setExpiration(new Date(date))
                .claim("sex","flame")
                .compact();

        System.out.println("token = " + token);

        String[] split = token.split("\\.");
        System.out.println("header = " + Base64Codec.BASE64.decodeToString(split[0]));
        System.out.println("payload = " + Base64Codec.BASE64.decodeToString(split[1]));
        System.out.println("sign = " + Base64Codec.BASE64.decodeToString(split[2]));


    }

    @Test
    public void testParseJwt() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJpYXQiOjE2NDU2ODUwMzksInN1YiI6InVzZXJfangiLCJleHAiOjE2NDY4OTQ2MzksInNleCI6ImZsYW1lIn0.iAXsDu8Pv-0CQOwGUx3lbOiD2OGV54NclWwwc9qCiYs";

        Claims claims = (Claims) Jwts.parser()
                .setSigningKey("jwt_key")
                .parse(jwt)
                .getBody();

        System.out.println("claims.getId() = " + claims.getId());
        System.out.println("claims.getSubject() = " + claims.getSubject());
        System.out.println("claims.getIssuedAt() = " + claims.getIssuedAt());
        System.out.println("claims.get(\"sex\") = " + claims.get("sex"));


    }

    @Test
    public void testEncoder(){
        String encode = passwordEncoder.encode("123");
        System.out.println("encode = " + encode);

    }

}
