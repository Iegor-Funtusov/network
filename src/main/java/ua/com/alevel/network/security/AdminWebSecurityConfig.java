package ua.com.alevel.network.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Iehor Funtusov, created 25/12/2020 - 9:50 AM
 */

@Order(1)
@Configuration
@EnableWebSecurity
public class AdminWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService adminDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminWebSecurityConfig(UserDetailsService adminDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        super();
        this.adminDetailsService = adminDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    @Primary
    @Qualifier("adminAuthenticationManager")
    public AuthenticationManager adminAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/admin/**")
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**").permitAll()
                .anyRequest().hasRole("ADMIN")

                .and().formLogin().loginPage("/admin/login")
                .defaultSuccessUrl("/admin/dashboard", true)
                .failureUrl("/admin/accessdenied")

                .and().logout().logoutUrl("/admin/logout").logoutSuccessUrl("/admin/login")

                .and().exceptionHandling().accessDeniedPage("/admin/accessdenied");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}
