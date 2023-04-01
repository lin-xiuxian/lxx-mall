package com.lxx.mall.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lxx.mall.common.Constant;
import com.lxx.mall.exception.LxxMallException;
import com.lxx.mall.exception.LxxMallExceptionEnum;
import com.lxx.mall.model.pojo.User;
import com.lxx.mall.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 林修贤
 * @date 2023/2/12
 * @description 用户校验过滤器
 */
public class UserFilter implements Filter {
    public static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();
    public User currentUser = new User();
    @Autowired
    UserService userService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String uri = request.getRequestURI().toString();
        if("OPTIONS".equals(request.getMethod()) || uri.contains("pay")){
            //对预检请求放行
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String token = request.getHeader(Constant.JWT_TOKEN);
            if (token == null){
                PrintWriter out = new HttpServletResponseWrapper((HttpServletResponse) servletResponse).getWriter();
                out.write("{\n" +
                        "    \"status\": 10007,\n" +
                        "    \"msg\": \"NEED_JWT_TOKEN\",\n" +
                        "    \"data\": null\n" +
                        "}");
                out.flush();
                out.close();
                return;
            }
            Algorithm algorithm = Algorithm.HMAC256(Constant.JWT_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            try{
                DecodedJWT jwt = verifier.verify(token);
                currentUser.setId(jwt.getClaim(Constant.USER_ID).asInt());
                currentUser.setUsername(jwt.getClaim(Constant.USER_NAME).asString());
                currentUser.setRole(jwt.getClaim(Constant.USER_ROLE).asInt());
                userThreadLocal.set(currentUser);
            } catch (TokenExpiredException e){
                //token过期，抛出异常
                throw new LxxMallException(LxxMallExceptionEnum.TOKEN_EXPIRED);
            } catch (JWTDecodeException e){
                //解码失败
                throw new LxxMallException(LxxMallExceptionEnum.TOKEN_WRONG);
            }

            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
