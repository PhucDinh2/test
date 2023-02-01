package projectdemo.com.democompany.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import projectdemo.com.democompany.service.UserService;

@Configuration
@EnableWebSecurity
public class UserConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public UserDetailsService getUserDetailsService() {
        return new UserService();
    }

    @Bean
    public BCryptPasswordEncoder getPassword() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
        return bCryptPasswordEncoder;
    }

    @Bean
    public DaoAuthenticationProvider daoProvider() {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(getUserDetailsService());
        dao.setPasswordEncoder(getPassword());
        return dao;
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect dialect = new SpringSecurityDialect();
        return dialect;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getPassword());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "register")
                .permitAll()
                .antMatchers("/user/**").access("hasAnyRole('ADMIN')")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/dologin").defaultSuccessUrl("/user")
                .failureUrl("/login?error=true")
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();
    }
}

"/authenticate","/api/register","/api/login","/api/get-all-product",
        "/api/get-all-orders","/api/get-all-productCategory","/api/get-all-user"