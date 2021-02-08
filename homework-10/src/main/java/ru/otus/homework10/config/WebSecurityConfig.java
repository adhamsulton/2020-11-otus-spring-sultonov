//package ru.otus.homework10.config;
//
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .requestMatchers()
//                .antMatchers("/login", "/logout", "/oauth/authorize")
//                .and()
//                .logout()
//                .deleteCookies("JSESSIONID")
//                .permitAll()
//                .and()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .csrf()
//                .disable();
//
//        http.cors().disable();
//
//    }
//}
