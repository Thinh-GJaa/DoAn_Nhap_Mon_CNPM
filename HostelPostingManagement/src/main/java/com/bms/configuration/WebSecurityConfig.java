package com.bms.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.bms.services.AccountDetailsServiceImpl;

@EnableWebSecurity
public class WebSecurityConfig {
	@Bean
	public UserDetailsService accountService() {
		return new AccountDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.rememberMe();
		http.authorizeRequests()
				.antMatchers("/home", "/view-posts", "/add-post", "/updatePost", "/approve-post", "/hidden-post",
						"/show-post", "/all-list-post-wait-approve", "/all-list-post-hidden", "/all-list-post-report",
						"/list-user-post-wait-approve", "/list-user-post-hidden", "/list-user-post", "/detail-post",
						"/list-user-save-post", "/user-profile", "/block-user", "/active-user", "/all-user",
						"/add-save-post")
				.hasAnyRole("ADMIN", "OWN", "TENANT").antMatchers("/resources/**").permitAll()
				
				.and()
				.formLogin()
				.loginPage("/login").usernameParameter("username").passwordParameter("password")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/home").permitAll()
				.failureUrl("/loginFailed")
				
				.and()
				.logout().logoutUrl("/logout")
//				.invalidateHttpSession(true)
//				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/login").permitAll()
				.and().csrf().disable();

		/*
		 * CSRF hay còn gọi là kỹ thuật tấn công “Cross-site Request Forgery“, nghĩa là
		 * kỹ thuật tấn công giả mạo chính chủ thể của nó
		 */

		return http.build();
	}
}
