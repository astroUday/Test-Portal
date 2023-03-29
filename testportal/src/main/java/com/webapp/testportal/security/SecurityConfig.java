package com.webapp.testportal.security;

import com.webapp.testportal.daoservice.serviceimpl.UserServiceImpl;
import com.webapp.testportal.filters.CustomAuthenticationFilter;
import com.webapp.testportal.filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.webapp.testportal.TestPortalApplication.passwordEncoder;

@Configuration
@EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig{

    private final UserDetailsService userDetailsService;
    @Autowired
    private final UserServiceImpl userService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        CustomAuthenticationFilter customAuthenticationFilter=
                new CustomAuthenticationFilter(userService,authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)));

        customAuthenticationFilter.setFilterProcessesUrl("/user/login/");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //permits
        http.authorizeHttpRequests().requestMatchers("/api-docs/**","/swagger.json","/configuration/ui","/swagger-ui/**","/swagger-resources","/configuration/security","/webjars/**").permitAll();
        http.authorizeHttpRequests().
                requestMatchers("/user/login/**","/user/register/**","/user/logout","/category/**","/quiz/**","/question/**").permitAll();
        http.authorizeHttpRequests().requestMatchers(HttpMethod.GET,"/user/delete/**").hasAnyAuthority("ADMIN");
        http.authorizeHttpRequests().requestMatchers(HttpMethod.GET,"/user/**").hasAnyAuthority("ADMIN");

        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(userService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
