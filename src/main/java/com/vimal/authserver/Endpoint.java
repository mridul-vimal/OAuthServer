package com.vimal.authserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("rest")
@Slf4j
public class Endpoint {

    private static int max=5;
    private static int min=1;
    public static final String TOKEN= "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2V4YW1wbGUuYXV0aDAuY29tLyIsImF1ZCI6Imh0dHBzOi8vYXBpLmV4YW1wbGUuY29tL2NhbGFuZGFyL3YxLyIsInN1YiI6InVzcl8xMjMiLCJpYXQiOjE0NTg3ODU3OTYsImV4cCI6MTQ1ODg3MjE5Nn0.CA7eaHjIHz5NxeIJoFK9krqaeZrPLwmMmgI_XiQiIkQ";

    @Value("${app.oauth.token.key}")
    private String tokenKey;

    @PostMapping("/oauth/access_token")
    public ResponseEntity<Map<String,Object>> generateAccessToken(HttpServletResponse response){
        log.info("Inside generateAccessToken {} ",random());
        response.setHeader(tokenKey,TOKEN);
        Map<String,Object> map = new HashMap<>();
        map.put("status","processed");
        map.put("success", true);
        return ResponseEntity.ok(map);
    }

    public static int random() {
        return  (int)Math.floor(Math.random()*(max-min+1)+min);
    }
}
