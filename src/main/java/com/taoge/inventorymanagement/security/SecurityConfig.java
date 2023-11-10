package com.taoge.inventorymanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig
{
	@Bean
	GrantedAuthorityDefaults grantedAuthorityDefaults()
	{
		return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
	}

	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource)
	{
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

		jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT username, password, active_status FROM users WHERE username = ?");

		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT username, role FROM roles WHERE username = ?");

		return jdbcUserDetailsManager;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
	{
		httpSecurity.authorizeHttpRequests(configurer ->
				configurer
						.requestMatchers("/").permitAll()
						.requestMatchers("/inventory").hasRole("EMPLOYEE")
						.requestMatchers("/inventory/**").hasRole("MANAGER")
						.requestMatchers("/access-denied").permitAll()
		).formLogin(form ->
				form.permitAll()
		).logout(logout ->
				logout.permitAll()
		).exceptionHandling(configurer ->
				configurer.accessDeniedPage("/access-denied")
		);

		return httpSecurity.build();
	}
}
