package com.poly.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

import com.poly.dao.AccountDAO;

@Configuration
public class SecurityConfig {
	@Autowired
	AccountDAO aDAO;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeRequests(authorizeRequests ->
	            authorizeRequests
	            .requestMatchers("/cart", "/order","/rest/cart/add/**","/profile").authenticated()
	            .requestMatchers("/home", "/about", "/service", "/contact", "/rest/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
	        )
	        .formLogin(form -> form
	        		.loginPage("/login")
	        	    .loginProcessingUrl("/login")
	        	    .failureUrl("/login/?error=true")
    		)
	        .logout(logout -> logout
	            .logoutUrl("/logout")
	            .deleteCookies("JSESSIONID")
	        );
	    
	    return http.build();
	}
//	@Bean
//    public LogoutFilter logoutFilter() {
//        LogoutFilter logoutFilter = new LogoutFilter("/logout", new SecurityContextLogoutHandler());
//        logoutFilter.setFilterProcessesUrl("/register");
//        return logoutFilter;
//    }
	@Bean
    public RestTemplate	 restTemplate() {
        return new RestTemplate();
    }
	public static UserDetails getUser() {
		UserDetails uDetail = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getPrincipal() instanceof UserDetails) {
			uDetail = (UserDetails) auth.getPrincipal();
		}
		return uDetail;
	}
}
