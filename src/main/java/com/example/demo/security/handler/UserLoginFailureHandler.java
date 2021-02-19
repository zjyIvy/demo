package com.example.demo.security.handler;

import com.example.demo.utils.HttpResult;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 *  @Description 登录失败处理类
 */
@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler {

    /**
     * 登录失败返回结果
     * @Author Sans
     * @CreateTime 2019/10/3 9:12
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception){
        // 这些对于操作的处理类可以根据不同异常进行不同处理
        Map<String,Object> resultData = new HashMap<>();
        resultData.put("code",500);

        if (exception instanceof UsernameNotFoundException){
            resultData.put("msg", "用户名不存在");
        }else if (exception instanceof BadCredentialsException){
            resultData.put("msg", "用户名密码不正确");
        }else {
            resultData.put("msg", "登录失败");
        }
        HttpResult.responseJson(response,resultData);
    }

}
