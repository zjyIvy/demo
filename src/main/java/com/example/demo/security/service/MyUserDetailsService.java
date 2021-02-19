package com.example.demo.security.service;

import com.example.demo.entity.SysUser;
import com.example.demo.mapper.SysPermissionMapper;
import com.example.demo.mapper.SysRoleMapper;
import com.example.demo.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.*;

/**
 * 自定义UserDetailsService
 * 用于返回用户名密码和权限
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysPermissionMapper permissionMapper;

    /***
     * 将用户信息和权限注入到Sercurity中
     * @param username 用户名称
     * @return UserDetails实现类
     * @throws UserDetails 用户名称，用户密码，权限集
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 查询出用户信息
        SysUser user = userMapper.selectByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在！");
        }

        Collection<GrantedAuthority> authorities = new HashSet<>();

        // 查询该用户拥有的角色
        List<Map<String, Object>> roleList = roleMapper.findRoleByUserIdMap(user.getId());
        for (Map<String, Object> roleMap : roleList) {

            String roleCode = roleMap.get("code").toString();

            if (!StringUtils.isEmpty(roleCode)) {
                authorities.add(new SimpleGrantedAuthority(roleCode));
            }
        }

        // 查询该用户角色拥有的权限
        List<Map<String, Object>> permList = permissionMapper.findPermByUserIdMap(user.getId());
        for (Map<String, Object> permMap : permList) {

            String permCode = permMap.get("code").toString();

            if (!StringUtils.isEmpty(permCode)) {
                authorities.add(new SimpleGrantedAuthority(permCode));
            }
        }

        // 查询该用户特有的权限
        List<Map<String, Object>> userPermList = permissionMapper.findUserPermByUserIdMap(user.getId());
        for (Map<String, Object> userPermMap : userPermList) {

            String userPermCode = userPermMap.get("code").toString();

            if (!StringUtils.isEmpty(userPermCode)) {
                authorities.add(new SimpleGrantedAuthority(userPermCode));
            }
        }

        // 返回UserDetails实体类，用户名称，用户密码，权限集
        return new User(user.getLoginName(),user.getLoginPw(),authorities);
    }
}
