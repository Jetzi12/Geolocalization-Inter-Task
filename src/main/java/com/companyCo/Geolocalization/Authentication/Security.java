package com.companyCo.Geolocalization.Authentication;

import com.companyCo.Geolocalization.Model.Users;
import com.companyCo.Geolocalization.Repository.GeoRepository;
import org.apache.log4j.BasicConfigurator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
public class Security extends WebSecurityConfigurerAdapter {

    private enum privilage {
        ADMIN, USER, STAFF
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(
                        new User("admin",
                                passwordEncoder().encode("admin"),
                                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + privilage.ADMIN))));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/localization/wholeList").hasRole(privilage.ADMIN.toString())
                .and()
                .formLogin().permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public void log4JConfig() {
        BasicConfigurator.configure();
    }

}
