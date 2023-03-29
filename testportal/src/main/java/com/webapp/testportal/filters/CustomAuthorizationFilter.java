package com.webapp.testportal.filters;


import com.webapp.testportal.entity.Authority;
import com.webapp.testportal.entity.Users;
import com.webapp.testportal.exception.CustomApiExceptionHandler;
import com.webapp.testportal.daoservice.serviceimpl.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.stream;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private UserServiceImpl userService;

    public CustomAuthorizationFilter(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal
            (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if((request.getServletPath().contains("/swagger-ui/")|| request.getServletPath().contains("/api-docs/") )|| request.getServletPath().equals("/user/logout")){
            filterChain.doFilter(request,response); return;
        }
        if(request.getServletPath().contains("/webjars/") || request.getServletPath().contains("/swagger-resources/")){
            filterChain.doFilter(request,response);return;
        }
        if(request.getServletPath().contains("/swagger.json") || request.getServletPath().contains("/configuration/ui")){
            filterChain.doFilter(request,response);return;
        }
        if(request.getServletPath().contains("/configuration/security")){
            filterChain.doFilter(request,response);return;
        }
        // passing request if login or register page
        if(request.getServletPath().equals("/user/login/") || request.getServletPath().equals("/user/register/")){
            System.out.println("Doing do filter");
            filterChain.doFilter(request,response);return;
        }
        // throwing exception in empty/wrong UUID
        if(request.getHeader("Authorization")==null || !request.getHeader("Authorization").startsWith("Bearer ")){
            System.out.println("Doing do filter 2.0");
            // response.sendRedirect("/user/login/");
            throw new CustomApiExceptionHandler(HttpStatus.BAD_REQUEST,"Please give Valid Token Id");
        }

        // authorizing
        System.out.println(" in CustomAuthorizationFilter");
        Collection<Authority> authorities=new ArrayList<>();
        String username=request.getHeader("username");
        String authHead=request.getHeader("Authorization");
        Long id=userService.findIdFromUUID(authHead.substring(7));

        System.out.println(id);
        Users user = userService.getUserById(id).get();

        user.getUserRoleSet().forEach(role -> {
            authorities.add(new Authority(role.getRole().getRoleName()));
        });

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                new UsernamePasswordAuthenticationToken(user,null,authorities);

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        System.out.println(" Doing do filter for curr user");
        filterChain.doFilter(request,response);
    }
}
