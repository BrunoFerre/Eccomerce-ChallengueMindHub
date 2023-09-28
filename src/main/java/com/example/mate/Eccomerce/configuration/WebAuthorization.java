package com.example.mate.Eccomerce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
public class WebAuthorization {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                .antMatchers("/web/**").permitAll()

                .antMatchers(HttpMethod.POST, "/api/login", "/api/person/add").permitAll()

                .antMatchers(HttpMethod.GET, "api/product/", "api/product/{id}", "api/product/{category}").permitAll()

                .antMatchers(HttpMethod.POST, "/api/comment/add", "/api/answer/add", "/api/punctuation/add").hasAuthority("CLIENT")

                .antMatchers(HttpMethod.PATCH, "/api/comment/update/{id}", "/api/answer/update/{id}").hasAuthority("CLIENT")

                .antMatchers(HttpMethod.DELETE, "/api/comment/delete/{id}", "/api/answer/delete/{id}").hasAuthority("CLIENT")

                .antMatchers(HttpMethod.POST, "/api/products/add").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.PATCH, "/api/products/{id}/stock", "/api/products/{id}/discount", "/api/products/{id}/price").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.DELETE, "/api/products/{id}").hasAuthority("ADMIN")

                .anyRequest().denyAll();

        http.formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/api/login");

        http.logout().logoutUrl("/api/logout");

        // turn off checking for CSRF tokens
        http.csrf().disable();

        //disabling frameOptions so h2-console can be accessed
        http.headers().frameOptions().disable();

        // if user is not authenticated, just send an authentication failure response
        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if login is successful, just clear the flags asking for authentication
        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

        // if login fails, just send an authentication failure response
        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if logout is successful, just send a success response
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

        return http.build();
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }
    }
}
