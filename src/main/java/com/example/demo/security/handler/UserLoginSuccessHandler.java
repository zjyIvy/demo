package com.example.demo.security.handler;

import com.example.demo.utils.HttpResult;
import com.example.demo.utils.JwtTokenUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 登录成功处理类
 */
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
    /**
     * 登录成功返回结果
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        // 组装JWT
        UserDetails userDetails =  (UserDetails) authentication.getPrincipal();
        String token = JwtTokenUtil.createAccessToken(userDetails);
        // 封装返回参数
        Map<String,Object> resultData = new HashMap<>();
        resultData.put("code",200);
        resultData.put("msg", "登录成功");
        resultData.put(JwtTokenUtil.TOKEN_HEADER,token);
        response.addHeader("Access-Control-Expose-Headers",JwtTokenUtil.TOKEN_HEADER);
        response.setHeader(JwtTokenUtil.TOKEN_HEADER,token);
        HttpResult.responseJson(response,resultData);
    }

}