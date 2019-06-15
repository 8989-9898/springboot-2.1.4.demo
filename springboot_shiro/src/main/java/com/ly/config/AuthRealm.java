package com.ly.config;

import com.ly.entity.User;
import com.ly.repository.DBCache;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.*;

/**
 * @ProjectName: springboot-2.1.4.demo
 * @Package: com.ly.config
 * @ClassName: AuthRealm
 * @Author: lin
 * @Description: ${description}
 * @Date: ${date} ${time}
 * @Version: 1.0
 */
public class AuthRealm extends AuthorizingRealm {


    /**
     * 只有需要验证权限是才会使用，授权查询回调函数，进行鉴权单缓存中午用户的授权信息是调用，在配置有缓存的情况下，只加载一次
     * 如果需要动态权限，但是有不想每次去数据库校验，可以存在ehcache中，自行完善
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Session session = SecurityUtils.getSubject().getSession();
        User user=(User)session.getAttribute("USER_SESSION");
        // 权限信息对象 info ，用来存放查出的用户的首要的角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles=new HashSet<>();
        roles.add(user.getRoleName());
        simpleAuthorizationInfo.setRoles(roles);

        // 用户的角色对应的所有权限，如果只使用角色定义访问权限，下面可以不要
        // 角色的粒度不够细 ，而权限的粒度可以精细到一个按钮或者操作选项
        Collection<String> strings = DBCache.CACHE_ROLE.get(user.getRoleName());
        simpleAuthorizationInfo.addStringPermissions(strings);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证回调函数，登录时调用
     * 首先根据传入的用户名获取 User 信息，如果为空则抛出 UnknownAccountException
     * 如果用户被锁定，抛出 LockAccountException
     * 最后生成AuthenticationInfo信息
     * 交给简介父类 AuthenticatingRealm 使用 CredentialsMatcher进行密码是否真确认证
     * 如果不匹配抛出 IncorrectCredentialsException
     * 另外如果密码重试太多将抛出超出重试次数异常 ExcessiveAttemptsException
     * 在组装 SimpleAuthenticationInfo时，需要传入：用户名，密文密码，盐
     * CredentialsMatcher 使用盐加密传入的明文密码和此处的密文密码进行匹配
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String) token.getPrincipal();
        User user = Optional.ofNullable(DBCache.CACHE_USER.get(principal)).orElseThrow(UnknownAccountException::new);
        if (!user.isLocked()) {
            throw new LockedAccountException();
        }
        // 从数据库查询出来的账号密码，与用户输入的用户和密码对比
        // 当用户执行登录时，在方法的处理上要实现 user.login(token)
        // 然后会自动进入这个类进行认证
        // 交给 AuthenticatingRealm 使用 CredentialsMathcher 进行密码匹配，可以自己定义
        // 如果使用HashedCredentialsMatcher 这里的认证就要改成
        // SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, "密码", ByteSource.Util.bytes("密码盐"), getName());
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, user.getPassword(), getName());
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("USER_SESSION",user);
        return simpleAuthenticationInfo;
    }
}
