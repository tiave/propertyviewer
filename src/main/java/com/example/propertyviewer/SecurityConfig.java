package com.example.propertyviewer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.propertyviewer.web.UserService;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService userDetailsService;	
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
   	 	.antMatchers("/resources/**", "/saveuser").permitAll()
   	 	.antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/css/**", "/api/allproperties").permitAll() // Enable css when logged out 
        .anyRequest().authenticated()
        .and()
      .formLogin()
      .loginPage("/login")
          .defaultSuccessUrl("/properties", true)
          .permitAll()
          .and()
      .logout()
          .permitAll();

        http.headers().frameOptions().sameOrigin();
 
        return http.build();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}