package com.yjt.springcloud.demodb.security;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO
 * ClassName: JwtAuthorizationTokenFilter
 * Date: 2019-10-22 19:29
 * author Administrator
 * version V1.0
 */
@Slf4j
@Component
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    private final JwtUserDetailService userDetailsService;
    private final TokenUtil jwtTokenUtil;
    private final String tokenHeader;

    public JwtAuthorizationTokenFilter(@Qualifier("jwtUserDetailService")JwtUserDetailService userDetailsService, TokenUtil jwtTokenUtil,  @Value("${jwt.header}")String tokenHeader) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.tokenHeader = tokenHeader;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String requestHeader = httpServletRequest.getHeader(this.tokenHeader);
        String authToken = null;
        String userName = null;
        if(StringUtils.isNotBlank(requestHeader) && requestHeader.startsWith("Bearer ")){
            authToken = requestHeader.substring(7);
            try {
                userName = jwtTokenUtil.getUserNameFromToken(authToken);
            } catch (ExpiredJwtException e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }
        if(StringUtils.isNotBlank(userName) && SecurityContextHolder.getContext().getAuthentication() ==null){
            JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(userName);
            //认证通过
            if(jwtTokenUtil.validateToken(authToken,jwtUser)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(userName,null,jwtUser.getAuthorities());
                usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
