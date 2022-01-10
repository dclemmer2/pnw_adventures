package edu.greenriver.sdev.myspringproject.config;

import edu.greenriver.sdev.myspringproject.services.LoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final LoginService service;

    public SecurityConfiguration(LoginService service) {
        this.service = service;
    }

    /**
     * Provide Spring security (and configuration) with a
     * password hash algorithm.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configure the mechanism for authentication in this
     * method (how we determine who a user is).
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = passwordEncoder();

        //authenticate users with our own db records
        auth
                .userDetailsService(service)
                .passwordEncoder(encoder);
    }

    /**
     * Configure which files and folders are publicly available
     * in the app.
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //security should ignore any request to our resources directory or the H2 console
        web
                .ignoring().antMatchers("/resources/**")
                .and()
                .ignoring().antMatchers("/h2-console/**");
    }

    /**
     * Configures permissions (authorization) and the login/logout
     * routines on the app.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //   super.configure(http);
        http
                .authorizeRequests()
                    .antMatchers("/pnwAdventures/individual/{id}", "/pnwAdventures/summary",
                        "/pnwAdventures/createAdventure", "/pnwAdventures/edit/{id}", "/pnwAdventures/form",
                        "/pnwAdventures/delete/{id}", "/pnwAdventures/ideas", "/pnwAdventures/consumer", "/pnwAdventures/api/**")
                        .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                    .antMatchers("/pnwAdventures/admin/**")
                        .hasAnyAuthority("ROLE_ADMIN")
                    .antMatchers("/**")
                        .permitAll()
                    .and()
                        .formLogin()
                            .permitAll()
                            .loginPage("/pnwAdventures/login")
                            .defaultSuccessUrl("/pnwAdventures/default")
                            .failureUrl("/pnwAdventures/login?error=true")
                    .and()
                        .logout()
                            .permitAll()
                            .logoutUrl("/pnwAdventures/logout")
                            .logoutSuccessUrl("/pnwAdventures/login?logout=true")
                .and()
                    .csrf()
                    .disable();
    }
}
