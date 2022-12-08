package pl.rynski.usermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;
import pl.rynski.usermanagement.security.CustomUserDetailsService;
import pl.rynski.usermanagement.security.MyCustomDsl;
import pl.rynski.usermanagement.service.UserService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity {

	private final CustomUserDetailsService customUserDetailsService;
	private final UserService userService;
	private final Environment environment;
	private final PasswordEncoder passwordEncoder;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.apply(MyCustomDsl.customDsl(userService, environment)).and()
		.csrf().disable()
		.authorizeHttpRequests((autz) -> autz
				.antMatchers("/users/login", "/users", "/users/token/refresh").permitAll()
				.anyRequest().authenticated())
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
			      .userDetailsService(customUserDetailsService)
			      .passwordEncoder(passwordEncoder)
			      .and()
			      .build();
	}
}
