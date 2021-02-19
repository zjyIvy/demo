package com.example.demo.config.security;

import com.example.demo.security.handler.*;
import com.example.demo.security.interceptor.JwtAuthenticationTokenFilter;
import com.example.demo.security.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 编写security配置
 */
// 识别该类为配置类
@Configuration
// 开启security服务
@EnableWebSecurity
// 开启全局的细粒度方法级别权限控制功能
// prePostEnabled = true，支持@PreAuthorize("hasRole('ROLE_UC_REQUIREMENTS_SELECT')注解
// securedEnabled = true，支持@Secured({"ROLE_SELLERS_EDIT","ROLE_BUYERS_EDIT"})
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        // 去除 ROLE_ 前缀
        return new GrantedAuthorityDefaults("");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 密码加密方式
        return new BCryptPasswordEncoder();
    }

    /**
     * 实现认证管理器
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        });
    }

    /**
     * 限制可访问的页面
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors()
                .and()
                    .csrf().disable() //默认都会产生一个hiden标签,里面有安全相关的验证,防止请求伪造,暂时不需要
                    .authorizeRequests()// 方法有很多子方法，每个子匹配器将会按照声明的顺序起作用。
                    .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .antMatchers("/login").permitAll() // 登录不拦截
                    .anyRequest().authenticated()// 登陆后访问
                // 基于token，所以不需要session
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 配置登录地址
                .and()
                    .formLogin()
                    .loginPage("/login") // 指定登录路径页面
//                    .loginProcessingUrl("/login") //登录页面路径访问自带的
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll()// 登录相关的接口直接过
                    .successHandler(new UserLoginSuccessHandler())// 配置登录成功自定义处理类
                    .failureHandler(new UserLoginFailureHandler())// 配置登录失败自定义处理类
                // 配置登出地址
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessHandler(new UserLogoutSuccessHandler())// 配置用户登出自定义处理类
                // 配置没有权限自定义处理类
                .and()
                    .exceptionHandling()
                        .accessDeniedHandler(new UserAuthAccessDeniedHandler()) //用户访问没有授权资源
                // 禁用缓存
                .and()
                    .headers().cacheControl()
        ;

        // 使用自己定义的拦截机制，拦截jwt
        http.addFilterBefore(new JwtAuthenticationTokenFilter(this.authenticationManager()), UsernamePasswordAuthenticationFilter.class);

    }

    /**
     * 创建核心过滤器FilterChainProxy实例
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) {

        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers(
                "/v2/api-docs",//swagger api json
                "/swagger-resources/configuration/ui",//用来获取支持的动作
                "/swagger-resources",//用来获取api-docs的URI
                "/swagger-resources/configuration/security",//安全选项
                "/swagger-ui.html",
                "/webjars/**"
        );

    }
}
