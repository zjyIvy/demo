package com.example.demo.security.interceptor;

import com.example.demo.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * JWT接口请求校验拦截器
 * 请求接口时会进入这里验证Token是否合法和过期
 */
public class JwtAuthenticationTokenFilter extends BasicAuthenticationFilter{

    public JwtAuthenticationTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 获取请求头中JWT的Token
        String tokenHeader = request.getHeader("Authorization");

        //如果请求头中没有Authorization信息则直接放行了
        if(tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtil.TOKEN_PREFIX)){

            chain.doFilter(request, response);
            return;

        }

        String getToken = tokenHeader.replace(JwtTokenUtil.TOKEN_PREFIX, "");

        //如果请求头中有token,则进行解析，并且设置认证信息
        if(!JwtTokenUtil.isTokenExpired(getToken)){

            //设置上下文
            UsernamePasswordAuthenticationToken authentication = getAuthentication(getToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }else {

            Claims claims =  JwtTokenUtil.getClaimsFromToken(getToken);
            String userName = claims.getSubject();
//            Collection<GrantedAuthority> authorities = this.analysisRole(role);

        }

        super.doFilterInternal(request, response, chain);

    }

    private Collection<GrantedAuthority> analysisRole(String role){
        // 这一块待定，又更好的方法就改
        role = role.replace("[", "");
        role = role.replace("]", "");
        role = role.replace("\"authority\":", "");
        role = role.replace("{\"", "");
        role = role.replace("\"}", "");

        String[] roles =  role.split(",");

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (int i = 0; i < roles.length; i++) {
            authorities.add(new SimpleGrantedAuthority(roles[i]));
        }
        return authorities;
    }

    /**
     * 获取用户信息
     * @param token token信息
     * @return 用户信息
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token){

        String username = JwtTokenUtil.getUserName(token);
        // 获得权限 添加到权限上去
        String role = JwtTokenUtil.getUserRole(token);

        Collection<GrantedAuthority> authorities = this.analysisRole(role);

        if(username != null){
            return new UsernamePasswordAuthenticationToken(username, null,authorities);
        }

        return null;
    }

}
