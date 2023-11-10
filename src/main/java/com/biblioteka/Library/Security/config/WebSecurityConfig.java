package com.biblioteka.Library.Security.config;

import com.biblioteka.Library.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Autowired
    public DaoAuthenticationProvider daoAuthenticationProvider(BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/registration", "/confirm/**")
                        .permitAll()
                        .requestMatchers("/password-set/**", "/password-reset/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/book", "/author", "/book/**", "/author/**")
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .usernameParameter("username")
                        .passwordParameter("password"))
                .httpBasic();
        return http.build();
    }
}
