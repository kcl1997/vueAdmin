package com.kcl.security.filter;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kcl.common.jwt.JwtUtils;
import com.kcl.common.redis.RedisUtil;
import com.kcl.entity.SysUser;
import com.kcl.service.Impl.SysUserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.StrUtil;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.security.filter
 * 文件名   JwtAuthenticationFilter
 * 创建者
 * 创建时间: 2021/6/5 1:50 PM
 * 描述  除了/login, 其他界面都拦截，检查token是否合法
 */


public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    SysUserServiceImpl mSysUserService;
    @Autowired
    RedisUtil mRedisUtil;



    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String headerToken = request.getHeader("jwt-token"); //token
        System.out.println("jwt-token : " + headerToken);

        if(StrUtil.isBlankOrUndefined(headerToken)){
            chain.doFilter(request,response); //匿名访问，放行，看看是否进入公共的接口中
            return;
        }


        // SignatureVerificationException      token值不对,签名不一致，更改了
        // TokenExpiredException               过期异常
        // AlgorithmMismatchException:		   算法不匹配异常
        // InvalidClaimException:			   失效的payload异常
        String username;
        String password;
        try {
            DecodedJWT decodedJWT = JwtUtils.verify(headerToken);
            Map<String, Claim> claims = decodedJWT.getClaims();
            username = claims.get("username").asString();
            System.out.println("username : " + username);

        } catch (SignatureVerificationException e) {
            throw new RuntimeException("token值不对,签名不一致，更改了");
        }catch (TokenExpiredException e) {
            throw new RuntimeException("过期异常");
        }catch (AlgorithmMismatchException e) {
            throw new RuntimeException("算法不匹配异常");
        }catch (InvalidClaimException e) {
            throw new RuntimeException("失效的payload异常");
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException("未知异常");
        }


        //授权,redis里面未过期就取出，否则就重新查找一遍
        SysUser user = mSysUserService.getUserByUserName(username);

        List<GrantedAuthority> grantedAuthorities = null;
        String roleAndAuthorityCode = null;
        if(mRedisUtil.get("authority"+user.getId()) == null)
            roleAndAuthorityCode = mSysUserService.getUserRoleAndAuthorityCodeByUserId(user.getId());
        else
            roleAndAuthorityCode = (String)mRedisUtil.get("authority" + user.getId());
        grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roleAndAuthorityCode);
        System.out.println(roleAndAuthorityCode);


        //生成系统token
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
        //"强制" 增加 用户认证信息, 认证成功
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        System.out.println("认证成功！");

        chain.doFilter(request,response);

    }
}
