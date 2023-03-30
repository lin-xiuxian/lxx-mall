package com.lxx.mall.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lxx.mall.common.ApiRestResponse;
import com.lxx.mall.common.Constant;
import com.lxx.mall.exception.LxxMallException;
import com.lxx.mall.exception.LxxMallExceptionEnum;
import com.lxx.mall.model.pojo.User;
import com.lxx.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 林修贤
 * @date 2023/2/12
 * @description 管理员校验过滤器
 */
public class AdminFilter implements Filter {
    public User currentUser;
    @Autowired
    UserService userService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if ("OPTIONS".equals(request.getMethod())){
            //对预检请求放行
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String token = request.getHeader(Constant.JWT_TOKEN);
            if(StringUtils.isEmpty(token)){
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
            try {
                DecodedJWT jwt = verifier.verify(token);
                currentUser = new User();
                currentUser.setUsername(jwt.getClaim(Constant.USER_NAME).asString());
                currentUser.setId(jwt.getClaim(Constant.USER_ID).asInt());
                currentUser.setRole(jwt.getClaim(Constant.USER_ROLE).asInt());
                UserFilter.userThreadLocal.set(currentUser);
            } catch (TokenExpiredException e){
                throw new LxxMallException(LxxMallExceptionEnum.TOKEN_EXPIRED);
            } catch (JWTDecodeException e){
                throw new LxxMallException(LxxMallExceptionEnum.TOKEN_WRONG);
            }
            //校验是否是管理员
            boolean adminRole = userService.checkAdminRole(currentUser);
            if (adminRole){
                filterChain.doFilter(servletRequest, servletResponse);
            } else {

                PrintWriter out = new HttpServletResponseWrapper((HttpServletResponse) servletResponse).getWriter();
                out.write("{\n" +
                        "    \"status\": 10009,\n" +
                        "    \"msg\": \"NEED_ADMIN\",\n" +
                        "    \"data\": null\n" +
                        "}");
                out.flush();
                out.close();
            }
        }
    }

    @Override
    public void destroy() {
    }
}
