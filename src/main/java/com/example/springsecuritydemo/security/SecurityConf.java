package com.example.springsecuritydemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/").permitAll()
                .antMatchers("/users/**").hasRole("admin")
                .antMatchers(HttpMethod.GET,"/posts/**").hasAnyRole("admin","student")
                .and()
                .formLogin()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean // cette methode retourne un objet Java qui va etre placee dans le contexte de l'applicstion
    protected UserDetailsService userDetailsService(){
        UserDetails toky = User.builder()
                .username("toky")
                .password(getPasswordEncoder().encode("toky123"))
                .roles("student")
                .build();

        UserDetails lou = User.builder()
                .username("lou")
                .password(getPasswordEncoder().encode("hei"))
                .roles("admin")
                .build();
        return new InMemoryUserDetailsManager(toky,lou);
    }
}
