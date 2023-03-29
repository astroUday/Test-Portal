package com.webapp.testportal.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webapp.testportal.daoservice.serviceimpl.UserServiceImpl;
import com.webapp.testportal.entity.UserToken;
import com.webapp.testportal.entity.Users;
import com.webapp.testportal.exception.UserNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.context.annotation.Bean;
import org.springframework.core.log.LogMessage;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserServiceImpl userServiceImpl;
    private AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(UserServiceImpl userService, AuthenticationManager authenticationManager) {
        this.userServiceImpl=userService;
        this.authenticationManager=authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication
            (HttpServletRequest request,HttpServletResponse response) throws AuthenticationException{
        System.out.println("In CustomAuthenticationFilter");

         String username=request.getParameter("username");
         String password=request.getParameter("password");

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                new UsernamePasswordAuthenticationToken(username,password);

        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }


    @Override
    protected void successfulAuthentication
            (HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        System.out.println("In successfulAuthentication..");


        UUID uuid=UUID.randomUUID();
        User user= (org.springframework.security.core.userdetails.User) authResult.getPrincipal();

        Users user1= userServiceImpl.getUserByUsername(user.getUsername());
        // updating token table
        UserToken userToken=UserToken.builder().userr(user1).Uuid(uuid.toString()).build();
        //parent entity
        userServiceImpl.createUuid(userToken);

        new ObjectMapper().writeValue(response.getOutputStream(),uuid.toString());
    }
















    //    @Override
//    protected void doFilterInternal(
//            @Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain)
//            throws ServletException, IOException {
//
//
//
//
//
////            final String authHeader=request.getHeader("Authorization");
////            final String jwt;
////            final String username;
////
////            if(authHeader==null || !authHeader.startsWith("Bearer ")){
////                filterChain.doFilter(request,response); return;
////            }
////
////            jwt=authHeader.substring(7);
////            username=jwtService.extractUsername(jwt);
//
//    }
}
