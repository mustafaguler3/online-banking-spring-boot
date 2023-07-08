package com.example.demo.config;

import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private Environment env;
    private UserSecurityService userSecurityService;

    private static final String SALT = "salt";

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12,new SecureRandom(SALT.getBytes()));
    }

    private static final String[] PUBLIC_MATCHER = {
            "/webjars/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/",
            "/about",
            "/contact/**",
            "/error/**/*",
            "/console/**",
            "/signup"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(PUBLIC_MATCHER)
                .permitAll().anyRequest().authenticated();

        http.csrf().disable().cors().disable()
                .formLogin().failureUrl("/index?error")
                .defaultSuccessUrl("/userFront")
                .loginPage("/index").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/index").deleteCookies("remember-me").permitAll()
                .and()
                .rememberMe();
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
    }
}

















