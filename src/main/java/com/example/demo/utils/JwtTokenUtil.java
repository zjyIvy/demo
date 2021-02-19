package com.example.demo.utils;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * JWT工具类
 */
@Component
public class JwtTokenUtil {

    // 头部
    public static final String TOKEN_HEADER = "Authorization";
    // 前缀
    public static final String TOKEN_PREFIX = "sans-";
    // 签发者
    public static final String ISS = "ivy";
    //过期时间3小时
    private static final Long EXPIRATION = (60 * 60 * 3) * 1000L;
    // 密钥
    private static final String SECRET  = "jwtSecret";
    // 权限
    private static final String ROLE = "role";

    /**
     * 生成Token
     *
     * @param userDetails 用户信息
     * @return token
     */
    public static String createAccessToken(UserDetails userDetails) {
        // 登陆成功生成JWT
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)// 签名算法和密钥
                // 放入用户ID和用户名
                .setSubject(userDetails.getUsername())
                .claim(ROLE, JSON.toJSONString(userDetails.getAuthorities()))// 自定义属性 放入用户拥有权限
                .setIssuer(ISS) // 签发者
                .setIssuedAt(new Date())// 签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION ))// 失效时间
                .compact();
        return  JwtTokenUtil.TOKEN_PREFIX + token;
    }

    /**
     * 从token中获取密钥
     * @param token
     * @return
     */
    private static Claims getTokenBody(String token){
        Claims claims = null;
        try{
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch(ExpiredJwtException e){
            e.printStackTrace();
        } catch(UnsupportedJwtException e){
            e.printStackTrace();
        } catch(MalformedJwtException e){
            e.printStackTrace();
        } catch(SignatureException e){
            e.printStackTrace();
        } catch(IllegalArgumentException e){
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 从token中获取去掉前缀之后的用户名
     * @param token 令牌
     * @return 用户名
     */
    public static String getUserName(String token){
        String username;
        try {
            username = getTokenBody(token).getSubject();
        } catch (    Exception e){
            username = null;
        }
        return username;
    }

    /**
     * 从token中获取当前用户的权限
     * @param token token
     * @return 当前用户的权限
     */
    public static String getUserRole(String token){
        return (String) getTokenBody(token).get(ROLE);
    }

    /**
     * 从令牌中获取数据声明
     * @param token 令牌
     * @return 数据声明
     */
    public static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();// 获取过期token信息
        }
        return claims;
    }

    /**
     * 判断令牌是否过期
     * @param token 令牌
     * @return 是否过期
     */
    public static Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return expiration.before(new Date());
          }catch (Exception e) {
            return true;
        }
    }

    /**
     * 验证令牌
     * @param token 令牌
     * @param userDetails 用户
     * @return 是否有效
     */
    public static Boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
