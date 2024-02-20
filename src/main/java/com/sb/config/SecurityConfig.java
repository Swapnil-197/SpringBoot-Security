package com.sb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	// User Creation
		@Bean
		public UserDetailsService userDetailsService(PasswordEncoder encoder) {

			// InMemoryUserDetailsManager
			UserDetails manager = User.withUsername("manager").password(encoder.encode("123"))
					.roles("MANAGER", "EMPLOYEE").build();

			UserDetails employee = User.withUsername("employee").password(encoder.encode("123"))
					.roles("EMPLOYEE").build();

			return new InMemoryUserDetailsManager(manager, employee);
		}
		

		// https://docs.spring.io/spring-security/reference/servlet/configuration/java.html > new implementation 
		// Configuring HttpSecurity
		@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			return http.csrf().disable()
					.authorizeHttpRequests()
					.requestMatchers("/home","/contact").permitAll()
					.and()
					.authorizeHttpRequests().requestMatchers("/balance").authenticated()
					.and()
					.authorizeHttpRequests().requestMatchers("/myloan").authenticated()
					.and()
					.authorizeHttpRequests().requestMatchers("/statement").authenticated()
					.and().httpBasic() 
				//	.and().formlogin();//form
			        .and().build();		
			       
		}

		// Password Encoding
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

}
